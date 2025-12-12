package com.sahonmu.burger87.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.sahonmu.burger87.viewmodels.base.BaseViewModel

class MapViewModel: BaseViewModel() {
    var latLng by mutableStateOf(LatLng(0.0, 0.0))
    var zoom by mutableStateOf(14f)
    var cameraPosition by mutableStateOf(
        CameraPosition.fromLatLngZoom(latLng, zoom)
    )

    fun isEmptyLatLng(): Boolean {
        return latLng.latitude == 0.0 && latLng.longitude == 0.0
    }
}