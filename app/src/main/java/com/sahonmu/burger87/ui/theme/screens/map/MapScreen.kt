package com.sahonmu.burger87.ui.theme.screens.map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.sahonmu.burger87.ui.theme.SplashBackground
import com.sahonmu.burger87.viewmodels.MapViewModel

@Composable
fun MapScreen(
    navController: NavHostController,
    mapViewModel: MapViewModel = hiltViewModel()
) {


    val mapViewUiState = mapViewModel.mapViewUiState.collectAsState()

    val mapUiSettings = MapUiSettings(
        compassEnabled = false,
        zoomControlsEnabled = false,
        mapToolbarEnabled = false,
        indoorLevelPickerEnabled = false
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .navigationBarsPadding()
            .background(color = SplashBackground),
        contentAlignment = Alignment.Center
    ) {

        LaunchedEffect(Unit) {
//            mapViewModel.requestStoreList()
            mapViewModel.getStoreUseCase()
        }


//        val cameraPositionState = rememberCameraPositionState {
//            position = CameraPosition.fromLatLngZoom(seoul, 12f)
//        }


        GoogleMap(
            modifier = Modifier.fillMaxSize(),
//            cameraPositionState = cameraPositionState,
            uiSettings = mapUiSettings
        ) {
//            mapViewUiState.value.storeList.forEach { store ->
//                val latLng = LatLng(store.latitude, store.longitude)
//                Marker(
//                    state = MarkerState(position = latLng)
//                )
//            }
        }
    }
}

@Preview
@Composable
fun MapScreenPreview() {
    MapScreen(rememberNavController())
}

