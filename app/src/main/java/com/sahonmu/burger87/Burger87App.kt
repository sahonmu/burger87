package com.sahonmu.burger87

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.sahonmu.burger87.utils.log.LogTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class Burger87App : Application() {

    // qgdnsxoaozeipuxcltka ID
//    sb_secret_FkCzgMUaNy66I05SMsC0Tg_b4G-UoxW  APIKEY
//    lateinit var supabase: SupabaseClient

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        init()
    }

    private fun init() {
        initTimberLog()
//        initSupabase()

    }

    private fun initTimberLog() {
        Timber.plant(LogTree())
    }

//    fun initSupabase() {
//        supabase = createSupabaseClient(
//            supabaseUrl = "https://qgdnsxoaozeipuxcltka.supabase.co",
//            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InFnZG5zeG9hb3plaXB1eGNsdGthIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjQwNzMwNjQsImV4cCI6MjA3OTY0OTA2NH0.QIJbtm40ZA_0mdpdtugf4uf1nHf1tNtnM7tqXk4USDk"
//        ) {
//            install(Auth)
//            install(Storage)
//            install(Postgrest)
//        }
//    }
}