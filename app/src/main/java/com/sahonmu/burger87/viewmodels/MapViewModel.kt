package com.sahonmu.burger87.viewmodels

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.sahonmu.burger87.R
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

    fun selectedMarker(context: Context, store: Store): View {
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