package com.sahonmu.burger87.utils.math

import android.location.Location
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

object MathUtils {


    fun calculateDistance(
        lat1: Double, lon1: Double,
        lat2: Double, lon2: Double
    ): Double {
        val R = 6371000.0

        val lat1Rad = Math.toRadians(lat1)
        val lat2Rad = Math.toRadians(lat2)
        val deltaLat = Math.toRadians(lat2 - lat1)
        val deltaLon = Math.toRadians(lon2 - lon1)

        val a = (sin(deltaLat / 2) * sin(deltaLat / 2)
                + cos(lat1Rad) * cos(lat2Rad) * sin(deltaLon / 2) * sin(deltaLon / 2))

        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        return R * c
    }

    fun distanceBetween(
        lat1: Double, lon1: Double,
        lat2: Double, lon2: Double
    ): Double {
        val result = FloatArray(1)
        Location.distanceBetween(
            lat1, lon1,
            lat2, lon2,
            result
        )
        return result[0].toDouble()
    }

    fun formatMeterToKm(meter: Double): String {
        val km = meter / 1000.0
        val value = String.format("%.1f", km)
        return when {
            km < 1000 ->
                if (value.endsWith(".0")) {
                    "${value.dropLast(2)}km"
                } else {
                    "${value}km"
                }
            else -> "999km+"
        }
    }

}