package com.sahonmu.burger87.utils.bitmap

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import android.view.View.MeasureSpec
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory

object BitmapUtils {

    fun vectorToBitmap(context: Context, @DrawableRes resId: Int): BitmapDescriptor? {
        val vectorDrawable = ContextCompat.getDrawable(context, resId) ?: return null
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    fun vectorToBitmap(context: Context, drawableId: Int, width: Int, height: Int): BitmapDescriptor {
        val drawable = ContextCompat.getDrawable(context, drawableId)!!
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    fun vectorToBitmapDescriptor(context: Context, @DrawableRes id: Int, tintColor: Int? = null, widthPx: Int? = null, heightPx: Int? = null): BitmapDescriptor {
        val drawable = ContextCompat.getDrawable(context, id)!!.mutate()
        tintColor?.let { drawable.setTint(it) }

        val intrinsicW = drawable.intrinsicWidth.takeIf { it > 0 } ?:  (widthPx ?: 100)
        val intrinsicH = drawable.intrinsicHeight.takeIf { it > 0 } ?: (heightPx ?: 100)

        val targetW = widthPx ?: intrinsicW
        val targetH = heightPx ?: intrinsicH

        val bitmap = Bitmap.createBitmap(targetW, targetH, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    fun viewToBitmap(view: View): Bitmap {
        view.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED)
        val bitmap = Bitmap.createBitmap(
            view.measuredWidth, view.measuredHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
        view.draw(canvas)
        return bitmap
    }

    fun vectorToBitmap(
        context: Context,
        @DrawableRes drawableId: Int,
        sizePx: Int
    ): Bitmap {
        val drawable = ContextCompat.getDrawable(context, drawableId)!!
        drawable.setBounds(0, 0, sizePx, sizePx)

        val bitmap = Bitmap.createBitmap(sizePx, sizePx, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.draw(canvas)

        return bitmap
    }

}