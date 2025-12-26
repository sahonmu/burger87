package com.sahonmu.burger87.ui.theme.screens.map

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.sahonmu.burger87.R
import com.sahonmu.burger87.common.Constants
import com.sahonmu.burger87.enums.LoadState
import com.sahonmu.burger87.enums.Screens
import com.sahonmu.burger87.enums.TrackingState
import com.sahonmu.burger87.extensions.encode
import com.sahonmu.burger87.extensions.findActivity
import com.sahonmu.burger87.extensions.moveItem
import com.sahonmu.burger87.ui.theme.Base
import com.sahonmu.burger87.ui.theme.White
import com.sahonmu.burger87.ui.theme.base.rememberUiState
import com.sahonmu.burger87.ui.theme.permission.CheckPermission
import com.sahonmu.burger87.ui.theme.permission.LocationPermissionHandler
import com.sahonmu.burger87.ui.theme.screens.components.Alert
import com.sahonmu.burger87.ui.theme.screens.components.HeightMargin
import com.sahonmu.burger87.ui.theme.screens.components.Margin
import com.sahonmu.burger87.ui.theme.screens.components.RoundButton
import com.sahonmu.burger87.ui.theme.screens.components.WidthMargin
import com.sahonmu.burger87.ui.theme.screens.composableActivityViewModel
import com.sahonmu.burger87.utils.bitmap.BitmapUtils
import com.sahonmu.burger87.viewmodels.MainViewModel
import com.sahonmu.burger87.viewmodels.MapViewModel
import com.sahonmu.burger87.viewmodels.StoreViewModel
import com.sahonmu.burger87.viewmodels.base.LocationViewModel
import domain.sahonmu.burger87.enums.isOperation
import timber.log.Timber

@Preview
@Composable
fun MapScreenPreview() {
    MapScreen(rememberNavController())
}

@Composable
fun MapScreen(
    navController: NavHostController,
    storeViewModel: StoreViewModel = hiltViewModel()
) {

    val uiState = rememberUiState()
    val scope = uiState.scope
    val context = uiState.context

    val mainViewModel = composableActivityViewModel<MainViewModel>()
    val locationViewModel = composableActivityViewModel<LocationViewModel>()

    val mapViewModel: MapViewModel = viewModel()
    val storeMapUiState = storeViewModel.storeMapUiState.collectAsState().value

    val locationUiState = locationViewModel.locationUiState.collectAsState().value
    var showLocationPermission by rememberSaveable { mutableStateOf(false) }

    val pagerState = rememberPagerState(pageCount = {
        storeMapUiState.storeList.size
    })

    var trackingState by rememberSaveable { mutableStateOf(TrackingState.NONE) }
    var showAlert by rememberSaveable { mutableStateOf(false) }
    var showAlertMessage by rememberSaveable { mutableStateOf("") }
    var isCardVisible by rememberSaveable { mutableStateOf(true) }
    val cardHeight = 125.dp
    val offsetY by animateDpAsState(
        targetValue = if (isCardVisible) 0.dp else cardHeight,
        animationSpec = tween(durationMillis = 400)
    )

    var googleMap by remember { mutableStateOf<GoogleMap?>(null) }
    var selectedMarker by remember { mutableStateOf<Marker?>(null) }
    var myLocationMarker by remember { mutableStateOf<Marker?>(null) }

    val fusedClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

    LaunchedEffect(Unit) {
        storeViewModel.requestStoreList()
    }

    LaunchedEffect(storeMapUiState.storeList) {
        mainViewModel.clone(storeMapUiState.storeList)
    }

    LaunchedEffect(pagerState, storeMapUiState.storeList) {
        snapshotFlow { pagerState.currentPage }.collect { position ->
            storeMapUiState.selectedIndex.value = position
            googleMap?.let { map ->
                val store = storeMapUiState.storeList[position]
                storeMapUiState.selectedStore.value = store
                val latLng = LatLng(store.latitude, store.longitude)
                selectedMarker?.remove()
                val markerOption = mapViewModel.selectedMarkerOption(context = context, store = store)
                selectedMarker = map.addMarker(markerOption)
                map.animateCamera(CameraUpdateFactory.newLatLng(latLng))
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .navigationBarsPadding()
            .background(color = White),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            if (storeMapUiState.loadState == LoadState.FINISHED) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    ClusterMapView(
                        storeMapUiState = storeMapUiState,
                        mapViewModel = mapViewModel,
                        onMarkerClick = { store ->
                            storeMapUiState.selectedIndex.value =
                                storeMapUiState.storeList.indexOfFirst { it.id == store.id }
                            pagerState.moveItem(
                                scope = scope,
                                animate = false,
                                page = storeMapUiState.selectedIndex.value
                            )

                            googleMap?.let { map ->
                                selectedMarker?.remove()
                                val markerOption = mapViewModel.selectedMarkerOption(context = context, store = store)
                                selectedMarker = map.addMarker(markerOption)
                                val latLng = LatLng(store.latitude, store.longitude)
                                map.animateCamera(CameraUpdateFactory.newLatLng(latLng))
                                storeMapUiState.selectedStore.value = store
                            }
                            isCardVisible = true
                        },
                        onMapClick = {
                            isCardVisible = false
                            selectedMarker?.remove()
                            Timber.i("마커 삭제 맵클릭")
                        },
                        onEmptyCameraPosition = { store ->
                            val latLng = LatLng(store.latitude, store.longitude)
                            googleMap?.let { map ->
                                selectedMarker?.remove()
                                val markerOption = mapViewModel.selectedMarkerOption(context = context, store = store)
                                selectedMarker = map.addMarker(markerOption)
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18f))
                                storeMapUiState.selectedStore.value = store
                            }
                        },
                        onGoogleMap = {
                            googleMap = it
                            storeMapUiState.selectedStore.value?.let { store ->
                                selectedMarker?.remove()
                                val markerOption = mapViewModel.selectedMarkerOption(context = context, store = store)
                                selectedMarker = it.addMarker(markerOption)
                            }
                        }
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .offset(y = offsetY)
                            .padding(bottom = 15.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row {
                                Margin(modifier = Modifier.weight(1f))
                                RoundButton(
                                    modifier = Modifier.size(44.dp),
                                    painter = painterResource(id = R.drawable.ic_location),
                                    imageSize = 22.dp,
                                    color = White,
                                    borderColor = if(trackingState == TrackingState.NONE) White else Base,
                                    onClick = {
                                        if(CheckPermission.hasLocationPermission(context)) {
                                            if(trackingState == TrackingState.NONE) {
                                                locationViewModel.getCurrentLocation(
                                                    fusedClient = fusedClient,
                                                    onSuccess = { lat, lng ->
                                                        val latLng = LatLng(lat, lng)
                                                        if(locationViewModel.isEmptyLocation()) {
                                                            val bitmap = BitmapUtils.vectorToBitmap(
                                                                context = context,
                                                                drawableId = R.drawable.ic_my_location,
                                                                sizePx = 48
                                                            )
                                                            val icon = BitmapDescriptorFactory.fromBitmap(bitmap)
                                                            locationUiState.myLocationMarkerOption
                                                                .position(latLng)
                                                                .icon(icon)
                                                                .anchor(0.5f, 0.5f)
                                                                .zIndex(Constants.MarKerZIndex.MY_LOCATION)
                                                            myLocationMarker = googleMap?.addMarker(locationUiState.myLocationMarkerOption)
                                                        } else {
                                                            myLocationMarker?.position = latLng
                                                        }
                                                        googleMap?.animateCamera(CameraUpdateFactory.newLatLng(latLng))
                                                        locationViewModel.updateLocation(latLng)
                                                        trackingState = TrackingState.SHOW
                                                    },
                                                    onFail = {
                                                        showAlertMessage = "위치획득에 실패했습니다."
                                                        showAlert = true
                                                    }
                                                )
                                            } else {
                                                trackingState = TrackingState.NONE
                                                locationViewModel.resetLocation()
                                                myLocationMarker?.remove()
                                            }
//                                            locationViewModel.requestLocationUpdates(
//                                                fusedClient = fusedClient
//                                            )
                                        } else {
                                            showLocationPermission = true
                                        }
                                    }
                                )
                                WidthMargin(20.dp)
                            }
                            HeightMargin(16.dp)
                            SummaryPager(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(110.dp),
                                pagerState = pagerState,
                                storeList = storeMapUiState.storeList,
                                onClickStore = { store ->
                                    if (store.storeState.isOperation()) {
                                        navController.navigate("${Screens.STORE_DETAIL}/${store.encode()}")
                                    } else {
                                        showAlertMessage = "폐업된 점포입니다."
                                        showAlert = true
                                    }
                                }
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 20.dp, end = 20.dp),
                    ) {
                        MapFloatingButtonBox(
                            modifier = Modifier,
                            onMenu = { navController.navigate(Screens.MENU.route) },
                            onSearch = {
                                val encode =
                                    storeMapUiState.storeList.filter { it.storeState.isOperation() }
                                        .encode()
                                navController.navigate("${Screens.STORE_SEARCH}/${encode}")
                            },
                            onStoreList = { navController.navigate(Screens.STORE_LIST.route) },
                            onCluster = {
                                val boundBuilder = LatLngBounds.builder()
                                storeMapUiState.storeList.filter { it.isDomestic() }.forEach { store ->
                                    val point = LatLng(store.latitude, store.longitude)
                                    boundBuilder.include(point)
                                }
                                googleMap?.animateCamera(
                                    CameraUpdateFactory.newLatLngBounds(
                                        boundBuilder.build(),
                                        100,
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }
    }

    if (showAlert) {
        Alert(
            message = showAlertMessage,
            onDismissRequest = { showAlert = false }
        )
    }

    if(showLocationPermission) {
        LocationPermissionHandler(
            onPermissionGranted = {
                showLocationPermission = false
//                locationViewModel.requestLocationUpdates(
//                    fusedClient = fusedClient
//                )
                locationViewModel.getCurrentLocation(
                    fusedClient = fusedClient,
                    onSuccess = { lat, lng ->
                        val latLng = LatLng(lat, lng)
                        if(locationViewModel.isEmptyLocation()) {
                            val bitmap = BitmapUtils.vectorToBitmap(
                                context = context,
                                drawableId = R.drawable.ic_my_location,
                                sizePx = 48
                            )
                            val icon = BitmapDescriptorFactory.fromBitmap(bitmap)
                            locationUiState.myLocationMarkerOption
                                .position(latLng)
                                .icon(icon)
                                .anchor(0.5f, 0.5f)
                                .zIndex(Constants.MarKerZIndex.MY_LOCATION)
                            myLocationMarker = googleMap?.addMarker(locationUiState.myLocationMarkerOption)
                        } else {
                            myLocationMarker?.position = latLng
                        }
                        googleMap?.animateCamera(CameraUpdateFactory.newLatLng(latLng))
                        locationViewModel.updateLocation(latLng)
                        trackingState = TrackingState.SHOW
                    },
                    onFail = {
                        showAlertMessage = "위치획득에 실패했습니다."
                        showAlert = true
                    }
                )
            },
            onPermissionDenied = {
                showLocationPermission = false
            },
        )
    }

    var backPressedTime by remember { mutableLongStateOf(0L) }
    BackHandler {
        if (System.currentTimeMillis() - backPressedTime <= 2000L) {
            // 앱 종료
            context.findActivity().finish()
        } else {
            Toast.makeText(context, "뒤로 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()
    }

    DisposableEffect(Unit) {
        onDispose {
            locationViewModel.resetLocation()
            locationViewModel.removeLocationUpdates(fusedClient)
        }
    }
}







