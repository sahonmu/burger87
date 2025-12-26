package com.sahonmu.burger87.viewmodels

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.sahonmu.burger87.R
import com.sahonmu.burger87.common.Constants
import com.sahonmu.burger87.utils.bitmap.BitmapUtils
import com.sahonmu.burger87.viewmodels.base.BaseViewModel
import domain.sahonmu.burger87.enums.isOperation
import domain.sahonmu.burger87.vo.store.Store

class MapViewModel: BaseViewModel() {
    var latLng by mutableStateOf(LatLng(0.0, 0.0))
    var zoom by mutableStateOf(14f)
    var cameraPosition by mutableStateOf(
        CameraPosition.fromLatLngZoom(latLng, zoom)
    )

    fun isEmptyLatLng(): Boolean {
        return latLng.latitude == 0.0 && latLng.longitude == 0.0
    }

    fun selectedMarkerOption(context: Context, store: Store): MarkerOptions {
        val view = selectedMarkerView(context, store)
        val latLng = LatLng(store.latitude, store.longitude)
        return MarkerOptions()
            .position(latLng)
            .anchor(0.5f, 0.5f)
            .zIndex(Constants.MarKerZIndex.SELECTED_STORE_MARKER)
            .icon(BitmapDescriptorFactory.fromBitmap(BitmapUtils.viewToBitmap(view.rootView)))
    }

    private fun selectedMarkerView(context: Context, store: Store): View {
        val markerView: View = LayoutInflater.from(context).inflate(R.layout.view_marker_selected, null)
        val operationLayout: FrameLayout = markerView.findViewById(R.id.operationLayout)
        val closedLayout: FrameLayout = markerView.findViewById(R.id.closedLayout)
        val scoreTextView: TextView = markerView.findViewById(R.id.scoreTextView)
        operationLayout.visibility = if (store.storeState.isOperation()) View.VISIBLE else View.GONE
        closedLayout.visibility = if (store.storeState.isOperation()) View.GONE else View.VISIBLE
        scoreTextView.text = store.score.toString()
        return markerView
    }


}