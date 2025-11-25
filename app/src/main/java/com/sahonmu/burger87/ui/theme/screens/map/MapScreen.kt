package com.sahonmu.burger87.ui.theme.screens.map

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.sahonmu.burger87.ui.theme.SplashBackground

@Composable
fun MapScreen(
    navController: NavHostController,
) {

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

        val seoul = LatLng(37.5665, 126.9780)

        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(seoul, 12f)
        }

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = mapUiSettings
        ) {
            Marker(
                state = MarkerState(position = seoul),
                title = "서울",
                snippet = "서울 중심"
            )
        }
    }
}

@Preview
@Composable
fun MapScreenPreview() {
    MapScreen(rememberNavController())
}

