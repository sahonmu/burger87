package com.sahonmu.burger87.utils

import android.content.Context

object AppUtils {

    fun Context.getAppVersionName(): String? {
        return packageManager.getPackageInfo(packageName, 0).versionName
    }

    fun Context.getAppVersionCode(): Long {
        return packageManager.getPackageInfo(packageName, 0).longVersionCode
    }

}