package com.sahonmu.burger87.extensions

import androidx.navigation.NavController
import com.google.gson.Gson
import com.sahonmu.burger87.enums.Screens
import domain.sahonmu.burger87.vo.store.Store
import java.net.URLDecoder
import java.net.URLEncoder


fun Any.encode(): String {
    val gson = Gson()
    val json = gson.toJson(this)
    return URLEncoder.encode(json, "UTF-8")
}

fun String.decode(): Any {
    val decodedJson = URLDecoder.decode(this, "UTF-8")
    return Gson().fromJson(decodedJson, Store::class.java)
}