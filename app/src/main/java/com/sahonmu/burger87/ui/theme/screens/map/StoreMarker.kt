package com.sahonmu.burger87.ui.theme.screens.map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.sahonmu.burger87.R
import com.sahonmu.burger87.utils.bitmap.BitmapUtils
import domain.sahonmu.burger87.vo.store.Store

@Composable
fun StoreMarker(
    store: Store,
    onClick: () -> Unit = { }
) {

    val context = LocalContext.current
    val markerIcon = remember { BitmapUtils.vectorToBitmap(context, R.drawable.ic_icon_selected_burger) }

    val latLng = LatLng(store.latitude, store.longitude)
    Marker(
        tag = store.id,
        state = MarkerState(position = latLng),
        icon = markerIcon,
        onClick = {
            onClick()
            return@Marker false
        }
    )
}