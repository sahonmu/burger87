package com.sahonmu.burger87.ui.theme.screens.map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.sahonmu.burger87.enums.LoadState
import com.sahonmu.burger87.ui.theme.SplashBackground
import com.sahonmu.burger87.viewmodels.MapViewModel

@Composable
fun MapScreen(
    navController: NavHostController,
    mapViewModel: MapViewModel = hiltViewModel()
) {


    val mapViewUiState = mapViewModel.mapViewUiState.collectAsState().value

    val mapUiSettings = MapUiSettings(
        compassEnabled = false,
        zoomControlsEnabled = false,
        mapToolbarEnabled = false,
        indoorLevelPickerEnabled = false
    )

    val cameraPositionState = rememberCameraPositionState()
    val density = LocalDensity.current
    val paddingPx = with(density) { 100.dp.roundToPx() }
    var mapSize by remember { mutableStateOf(IntSize.Zero) }

    LaunchedEffect(mapViewUiState.storeList.size, mapSize) {

        if (mapSize.width == 0 || mapViewUiState.storeList.isEmpty())
            return@LaunchedEffect

        val builder = LatLngBounds.builder()

        mapViewUiState.storeList.forEach { store ->
            val point = LatLng(store.latitude, store.longitude)
            builder.include(point)
        }

        val bounds: LatLngBounds = builder.build()
        val update: CameraUpdate = CameraUpdateFactory.newLatLngBounds(
            bounds,
            mapSize.width,
            mapSize.height,
            paddingPx
        )

        cameraPositionState.animate(update)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .navigationBarsPadding()
            .onSizeChanged { size ->
                mapSize = size
            }
            .background(color = SplashBackground),
        contentAlignment = Alignment.Center
    ) {

        LaunchedEffect(Unit) {
            mapViewModel.requestStoreList()
        }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            if (mapViewUiState.loadState == LoadState.LOADING) {
                Text(
                    text = "로딩중"
                )
            } else {
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState,
                    uiSettings = mapUiSettings
                ) {
                    mapViewUiState.storeList.forEach { store ->
                        val latLng = LatLng(store.latitude, store.longitude)
                        Marker(
                            state = MarkerState(position = latLng)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MapScreenPreview() {
    MapScreen(rememberNavController())
}

