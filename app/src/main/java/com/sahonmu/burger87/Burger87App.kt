package com.sahonmu.burger87

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.sahonmu.burger87.utils.log.LogTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class Burger87App : Application() {


    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        init()
    }

    private fun init() {
        initTimberLog()
    }

    private fun initTimberLog() {
        Timber.plant(LogTree())
    }
}