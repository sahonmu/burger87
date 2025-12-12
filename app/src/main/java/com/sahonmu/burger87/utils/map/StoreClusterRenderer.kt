package com.sahonmu.burger87.utils.map

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.sahonmu.burger87.R
import com.sahonmu.burger87.utils.bitmap.BitmapUtils
import domain.sahonmu.burger87.enums.isOperation

class StoreClusterRenderer(
    context: Context,
    map: GoogleMap,
    clusterManager: ClusterManager<StoreClusterItem>,
) : DefaultClusterRenderer<StoreClusterItem>(context, map, clusterManager) {
    private val markerView: View =
        LayoutInflater.from(context).inflate(R.layout.view_marker, null)
    private val operationLayout: FrameLayout = markerView.findViewById(R.id.operationLayout)
    private val closedLayout: FrameLayout = markerView.findViewById(R.id.closedLayout)
    private val scoreTextView: TextView = markerView.findViewById(R.id.scoreTextView)


    private val clusterView: View =
        LayoutInflater.from(context).inflate(R.layout.view_cluster, null)
    private val clusterTextView: TextView = clusterView.findViewById(R.id.countTextView)


    init {

    }

    override fun onClusterItemRendered(
        item: StoreClusterItem,
        marker: Marker
    ) {
        super.onClusterItemRendered(item, marker)
        operationLayout.visibility = if (item.store.storeState.isOperation()) View.VISIBLE else View.GONE
        closedLayout.visibility = if (item.store.storeState.isOperation()) View.GONE else View.VISIBLE
        scoreTextView.text = item.store.score.toString()
        marker.setIcon(BitmapDescriptorFactory.fromBitmap(BitmapUtils.viewToBitmap(markerView.rootView)))
        marker.setAnchor(0.5f, 0.5f)
        marker.zIndex = if(item.store.storeState.isOperation()) 2f else 1f
    }

    // 개별 마커 설정
//    override fun onBeforeClusterItemRendered(item: StoreClusterItem, markerOptions: MarkerOptions) {
//        super.onBeforeClusterItemRendered(item, markerOptions)
//        operationLayout.visibility =
//            if (item.store.state == "operation") View.VISIBLE else View.GONE
//        closedLayout.visibility = if (item.store.state == "operation") View.GONE else View.VISIBLE
//        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapUtils.viewToBitmap(markerView.rootView)))
//    }

    override fun onClusterRendered(cluster: Cluster<StoreClusterItem?>, marker: Marker) {
        super.onClusterRendered(cluster, marker)
        val size = convertRangeLabel(cluster.size)
        clusterTextView.text = size
        marker.setIcon(BitmapDescriptorFactory.fromBitmap(BitmapUtils.viewToBitmap(clusterView.rootView)))
        marker.setAnchor(0.5f, 0.5f)
    }

//    override fun onBeforeClusterRendered(
//        cluster: Cluster<StoreClusterItem>,
//        markerOptions: MarkerOptions
//    ) {
////        super.onBeforeClusterRendered(cluster, markerOptions)
//        val size = convertRangeLabel(cluster.size)
//        clusterTextView.text = size
//        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapUtils.viewToBitmap(clusterView.rootView)))
//    }

//    override fun onClustersChanged(clusters: Set<Cluster<StoreClusterItem?>?>?) {
//        super.onClustersChanged(clusters)
//    }


    fun convertRangeLabel(value: Int): String {

        if (value <= 10) return value.toString()
        if (value in 101..<900) {
            val step = 100
            val base = (value / step) * step
            return "${base}+"
        }

        if (value >= 1000) return "999+"

        val step = 10
        val base = (value / step) * step  // 10단위 구간 계산

        return "${base}+"
    }
}