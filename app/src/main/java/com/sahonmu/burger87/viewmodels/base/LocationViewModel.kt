package com.sahonmu.burger87.viewmodels.base

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.sahonmu.burger87.R
import com.sahonmu.burger87.common.Constants
import com.sahonmu.burger87.enums.LoadState
import com.sahonmu.burger87.utils.bitmap.BitmapUtils
import com.sahonmu.burger87.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import domain.sahonmu.burger87.vo.store.Store
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.math.ln


data class LocationUiState(
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    var isGrantedLocationPermission: Boolean = false,
    var myLocationMarkerOption: MarkerOptions = MarkerOptions().apply {
        anchor(0.5f, 0.5f)
        zIndex(Constants.MarKerZIndex.MY_LOCATION)
    }
)

@HiltViewModel
class LocationViewModel @Inject constructor(
//    private val keyGenLocalDataSource: KeyGenLocalDataSource,
) : BaseViewModel() {

    private val _locationUiState = MutableStateFlow(LocationUiState())
    val locationUiState = _locationUiState.asStateFlow()


    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult) {
            val location = result.lastLocation
            if (location != null) {
                val lat = location.latitude
                val lng = location.longitude
                _locationUiState.update { state ->
                    state.copy(
                        latitude = lat,
                        longitude = lng,
                    )
                }
            }
        }
    }

    val locationRequest = LocationRequest.Builder(
        Priority.PRIORITY_HIGH_ACCURACY,
        3_000L // 3초
    )
        .setMinUpdateIntervalMillis(1_000L) // 최소 1초
        .setWaitForAccurateLocation(true)
        .build()

    fun createMyLocationMarkerOption(
        context: Context,
    ) {
        val bitmap = BitmapUtils.vectorToBitmap(
            context = context,
            drawableId = R.drawable.ic_my_location,
            sizePx = 48
        )
        val icon = BitmapDescriptorFactory.fromBitmap(bitmap)
        _locationUiState.value.myLocationMarkerOption
            .icon(icon)
    }

    fun isEmptyLocation(): Boolean {
        return locationUiState.value.latitude == 0.0 && locationUiState.value.longitude == 0.0
//        return locationUiState.value.myLocationMarkerOption.position == LatLng(0.0, 0.0)
    }

    fun requestLocationUpdates(
        fusedClient: FusedLocationProviderClient
    ) {
        fusedClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    fun removeLocationUpdates(fusedClient: FusedLocationProviderClient) {
        fusedClient.removeLocationUpdates(locationCallback)
    }

    fun getCurrentLocation(
        fusedClient: FusedLocationProviderClient,
        onSuccess: (lat: Double, lng: Double) -> Unit,
        onFail: () -> Unit
    ) {
        fusedClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            null
        ).addOnSuccessListener { location ->
            if (location != null) {
                onSuccess(location.latitude, location.longitude)
            } else {
                onFail()
            }
        }.addOnFailureListener {
            onFail()
        }
    }

    fun updateLocation(latitude: Double, longitude: Double) {
        locationUiState.value.latitude = latitude
        locationUiState.value.longitude = longitude
    }


    fun updateLocation(latLng: LatLng) {
        locationUiState.value.latitude = latLng.latitude
        locationUiState.value.longitude = latLng.longitude
    }

    fun resetLocation() {
        locationUiState.value.latitude = 0.0
        locationUiState.value.longitude = 0.0
    }
}