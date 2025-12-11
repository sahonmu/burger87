package com.sahonmu.burger87.ui.theme.screens.map

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.clustering.ClusterManager
import com.sahonmu.burger87.R
import com.sahonmu.burger87.ui.theme.base.rememberUiState
import com.sahonmu.burger87.utils.map.StoreClusterItem
import com.sahonmu.burger87.utils.map.StoreClusterRenderer
import com.sahonmu.burger87.viewmodels.MapViewModelUiState
import domain.sahonmu.burger87.vo.store.Store
import timber.log.Timber

@Composable
fun ClusterMapView(
    mapViewUiState: MapViewModelUiState,
    onMarkerClick: (Store) -> Unit = { }
) {

    val context = rememberUiState().context
    val mapView = rememberMapViewWithLifecycle()

    var googleMap by remember { mutableStateOf<GoogleMap?>(null) }
    var clusterManager by remember { mutableStateOf<ClusterManager<StoreClusterItem>?>(null) }


    AndroidView(
        factory = { mapView },
        update = { mv ->
            mv.getMapAsync { map ->
                if (googleMap == null) {
                    googleMap = map
                    mapSetting(context, map)

                    // ClusterManager 생성 및 렌더러 등록
                    clusterManager = ClusterManager<StoreClusterItem>(context, googleMap).apply {
                        val renderer = StoreClusterRenderer(
                            context,
                            map,
                            this,
                            R.drawable.ic_burger,
                            R.drawable.splash
                        )
                        this.renderer = renderer

                        // (선택) 클러스터 클릭 리스너
                        this.setOnClusterClickListener { cluster ->
                            // 예: 클러스터에 줌인
                            val position = cluster.position
                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 11f))
                            true
                        }

                        // (선택) 클러스터 아이템 클릭
                        this.setOnClusterItemClickListener { item ->
                            onMarkerClick(item.store)
                            map.animateCamera(CameraUpdateFactory.newLatLng(item.position))
                            true // false면 기본 마커 클릭 동작(InfoWindow 등) 계속
                        }

                        this.clearItems()
                        mapViewUiState.storeList.forEach { store ->
                            this.addItem(StoreClusterItem(store))
                        }
                        this.cluster()
                    }

//                    map.moveCamera(
//                        CameraUpdateFactory.newLatLngBounds(
//                            mapViewUiState.boundBuilder.build(),
//                            100,
//                        )
//                    )

                    // 클릭 리스너들을 ClusterManager에 연결
                    map.setOnCameraIdleListener(clusterManager)
                    map.setOnMarkerClickListener(clusterManager)
//                            gMap.setOnInfoWindowClickListener(clusterManager)
                }
            }

            Timber.i("selectedIndex = ${mapViewUiState.selectedIndex.value}")
            if (mapViewUiState.storeList.isNotEmpty()) {
                val store = mapViewUiState.storeList[mapViewUiState.selectedIndex.value]
                val latLng = LatLng(store.latitude, store.longitude)
                googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14.5f))
            }
        }
    )
}

@Composable
fun rememberMapViewWithLifecycle(): MapView {
    val context = LocalContext.current
    val mapView = remember {
        MapView(context)
    }

    // Hook into lifecycle
    val lifecycle = rememberUpdatedState(LocalContext.current)
    DisposableEffect(mapView) {
        // MapView 초기화
        mapView.onCreate(null)
        mapView.onResume()

        onDispose {
            mapView.onPause()
            mapView.onDestroy()
        }
    }
    return mapView
}

private fun mapSetting(context: Context, map: GoogleMap) {

    val KOREA_BOUNDS = LatLngBounds(
        LatLng(33.0, 124.0),  // SW (제주 남쪽~서쪽)
        LatLng(39.0, 132.0)   // NE (강원 북동쪽)
    )

    with(map.uiSettings) {
        isZoomControlsEnabled = false      // + - 버튼
        isCompassEnabled = false            // 나침반
        isMapToolbarEnabled = false        // 기본 네비게이션 툴바
        isMyLocationButtonEnabled = false  // 내 위치 버튼
        isRotateGesturesEnabled = false     // 두 손가락 회전
        isTiltGesturesEnabled = false       // 위/아래 기울이기
        isZoomGesturesEnabled = true       // 핀치 줌
        isScrollGesturesEnabled = true     // 드래그 이동
        isScrollGesturesEnabledDuringRotateOrZoom = false
    }
    map.apply {
        setMinZoomPreference(6f)
        setMaxZoomPreference(20f)
        isBuildingsEnabled = false
        isIndoorEnabled = false
        isTrafficEnabled = false
        setLatLngBoundsForCameraTarget(KOREA_BOUNDS)
        setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.map_style))
    }
}
