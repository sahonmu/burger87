package com.sahonmu.burger87.utils.map

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import domain.sahonmu.burger87.vo.store.Store

class StoreClusterItem(
    val store: Store
) : ClusterItem {

    private val pos = LatLng(store.latitude, store.longitude)
    private val tag = store.id

    override fun getPosition(): LatLng = pos
    override fun getTitle(): String = ""
    override fun getSnippet(): String = ""
    override fun getZIndex(): Float = 1f

}