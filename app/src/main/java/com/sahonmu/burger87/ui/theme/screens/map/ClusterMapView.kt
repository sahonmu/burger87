package com.sahonmu.burger87.ui.theme.screens.map

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.sahonmu.burger87.R
import com.sahonmu.burger87.ui.theme.base.rememberUiState
import com.sahonmu.burger87.utils.bitmap.BitmapUtils
import com.sahonmu.burger87.utils.map.StoreClusterItem
import com.sahonmu.burger87.utils.map.StoreClusterRenderer
import com.sahonmu.burger87.viewmodels.MapViewModel
import com.sahonmu.burger87.viewmodels.StoreMapUiState
import domain.sahonmu.burger87.enums.isOperation
import domain.sahonmu.burger87.vo.store.Store

@Composable
fun ClusterMapView(
    storeMapUiState: StoreMapUiState,
    mapViewModel: MapViewModel,
    onMarkerClick: (Store) -> Unit = { },
    onMapClick: () -> Unit = { },
    onGoogleMap: (GoogleMap) -> Unit = { },
) {

    val context = rememberUiState().context
    val mapView = rememberMapViewWithLifecycle()

    var googleMap by remember { mutableStateOf<GoogleMap?>(null) }
    var clusterManager by remember { mutableStateOf<ClusterManager<StoreClusterItem>?>(null) }

    var selectedMarker by remember { mutableStateOf<Marker?>(null) }
    val selectedIndex = storeMapUiState.selectedIndex.value

    AndroidView(
        factory = { mapView },
        update = { mv ->
            mv.getMapAsync { map ->
                if (googleMap == null) {
                    googleMap = map
                    mapSetting(context, map)
                    onGoogleMap(map)

                    // ClusterManager 생성 및 렌더러 등록
                    clusterManager = ClusterManager<StoreClusterItem>(context, googleMap).apply {
                        val renderer = StoreClusterRenderer(
                            context,
                            map,
                            this,
                        )
                        this.renderer = renderer

                        // (선택) 클러스터 클릭 리스너
                        this.setOnClusterClickListener { cluster ->
                            // 예: 클러스터에 줌인
//                            val position = cluster.position
//                                map.animateCamera(CameraUpdateFactory.newLatLngZoom(position, if(map.cameraPosition.zoom >= 11f) map.cameraPosition.zoom else 11f))
                            val boundBuilder = LatLngBounds.builder()
                            cluster.items.forEach { item ->
                                item?.store?.let { store ->
                                    val point = LatLng(store.latitude, store.longitude)
                                    boundBuilder.include(point)
                                }
                            }
                            map.animateCamera(
                                CameraUpdateFactory.newLatLngBounds(
                                    boundBuilder.build(),
                                    100,
                                )
                            )
                            true
                        }

                        // (선택) 클러스터 아이템 클릭
                        this.setOnClusterItemClickListener { item ->
                            onMarkerClick(item.store)
//                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(item.store.latitude, item.store.longitude)))
                            map.animateCamera(CameraUpdateFactory.newLatLng(item.position))

                            selectedMarker?.remove()
                            val view = selectedMarker(context, item.store)
                            val markerOption = MarkerOptions()
                                .position(item.position)
                                .anchor(0.5f, 0.5f)
                                .zIndex(5f)
                                .icon(BitmapDescriptorFactory.fromBitmap(BitmapUtils.viewToBitmap(view.rootView)))
                                selectedMarker = map.addMarker(markerOption)
                            true // false면 기본 마커 클릭 동작(InfoWindow 등) 계속
                        }

                        this.clearItems()
                        storeMapUiState.storeList.forEach { store ->
                            this.addItem(StoreClusterItem(store))
                        }
                        this.cluster()
                    }

                    // 클릭 리스너들을 ClusterManager에 연결
                    map.setOnCameraIdleListener(clusterManager)
                    map.setOnMarkerClickListener(clusterManager)
                    map.setOnMapClickListener {
                        selectedMarker?.remove()
                        onMapClick()
                    }

                    map.setOnCameraIdleListener {
                        val position = map.cameraPosition
                        mapViewModel.latLng = position.target        // LatLng
                        mapViewModel.zoom = position.zoom            // Float
                    }

                    if(mapViewModel.isEmptyLatLng()) {
                        val store = storeMapUiState.storeList[storeMapUiState.selectedIndex.value]
                        val latLng = LatLng(store.latitude, store.longitude)
                        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14.5f))
                        mapViewModel.latLng = latLng
                        mapViewModel.zoom = 14.5f
                    } else {
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(mapViewModel.latLng, mapViewModel.zoom))
                    }
                }

                selectedMarker?.remove()
                val store = storeMapUiState.storeList[selectedIndex]
                val view = selectedMarker(context, store)
                val markerOption = MarkerOptions()
                    .position(LatLng(store.latitude, store.longitude))
                    .anchor(0.5f, 0.5f)
                    .zIndex(5f)
                    .icon(BitmapDescriptorFactory.fromBitmap(BitmapUtils.viewToBitmap(view.rootView)))
                selectedMarker = map.addMarker(markerOption)

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

private fun selectedMarker(context: Context, store: Store): View {
    val markerView: View = LayoutInflater.from(context).inflate(R.layout.view_marker_selected, null)
    val operationLayout: FrameLayout = markerView.findViewById(R.id.operationLayout)
    val closedLayout: FrameLayout = markerView.findViewById(R.id.closedLayout)
    val scoreTextView: TextView = markerView.findViewById(R.id.scoreTextView)

    operationLayout.visibility = if (store.storeState.isOperation()) View.VISIBLE else View.GONE
    closedLayout.visibility = if (store.storeState.isOperation()) View.GONE else View.VISIBLE
    scoreTextView.text = store.score.toString()
    return markerView
}

