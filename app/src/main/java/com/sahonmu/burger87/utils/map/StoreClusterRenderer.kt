package com.sahonmu.burger87.utils.map

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.google.maps.android.ui.IconGenerator
import com.sahonmu.burger87.R
import com.sahonmu.burger87.utils.bitmap.BitmapUtils

class StoreClusterRenderer(
    context: Context,
    map: GoogleMap,
    clusterManager: ClusterManager<StoreClusterItem>,
    @DrawableRes private val singleIconRes: Int,
    @DrawableRes private val clusterIconRes: Int
) : DefaultClusterRenderer<StoreClusterItem>(context, map, clusterManager) {

    private val singleIcon: BitmapDescriptor by lazy {
        BitmapUtils.vectorToBitmapDescriptor(
            context,
            singleIconRes,
            widthPx = dpToPx(context, 40),
            heightPx = dpToPx(context, 40)
        )
    }
    private val clusterIcon: BitmapDescriptor by lazy {
        BitmapUtils.vectorToBitmapDescriptor(
            context,
            clusterIconRes,
            widthPx = dpToPx(context, 48),
            heightPx = dpToPx(context, 48)
        )
    }

//    private val iconGenerator = IconGenerator(context)
//    private val clusterGenerator = IconGenerator(context)

    //    private val markerView: ViewMarkerBinding = ViewMarkerBinding.inflate(LayoutInflater.from(context))
//    private var markerView: ViewMarkerBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_marker, null, false)
//    private val closedLayout: FrameLayout = markerView.operationLayout
//    private val closedLayout: FrameLayout = markerView.closedLayout
    private val markerView: View =
        LayoutInflater.from(context).inflate(R.layout.view_marker, null)
    private val operationLayout: FrameLayout = markerView.findViewById(R.id.operationLayout)
    private val closedLayout: FrameLayout = markerView.findViewById(R.id.closedLayout)
    private val scoreTextView: TextView = markerView.findViewById(R.id.scoreTextView)


    private val clusterView: View =
        LayoutInflater.from(context).inflate(R.layout.view_cluster, null)
    private val clusterTextView: TextView = clusterView.findViewById(R.id.countTextView)


    init {
//        iconGenerator.setContentView(markerView)
//        iconGenerator.setBackground(null)
//
//        clusterGenerator.setContentView(clusterView)
//        clusterGenerator.setBackground(null)
    }

    override fun onClusterItemRendered(
        item: StoreClusterItem,
        marker: Marker
    ) {
        super.onClusterItemRendered(item, marker)
        operationLayout.visibility = if (item.store.state == "operation") View.VISIBLE else View.GONE
        closedLayout.visibility = if (item.store.state == "operation") View.GONE else View.VISIBLE
        scoreTextView.text = item.store.score.toString()
        marker.setIcon(BitmapDescriptorFactory.fromBitmap(BitmapUtils.viewToBitmap(markerView.rootView)))
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


    fun dpToPx(context: Context, dp: Int): Int =
        (dp * context.resources.displayMetrics.density).toInt()

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