package com.sahonmu.burger87.utils

import android.content.Context
import android.content.pm.PackageManager

object AppUtils {

    fun Context.getAppVersionName(): String? {
        return packageManager.getPackageInfo(packageName, 0).versionName
    }

    fun Context.getAppVersionCode(): Long {
        return packageManager.getPackageInfo(packageName, 0).longVersionCode
    }

    fun isAppInstalled(context: Context, packageName: String): Boolean {
        return try {
            context.packageManager.getPackageInfo(packageName, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

}