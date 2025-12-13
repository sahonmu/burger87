package com.sahonmu.burger87.extensions

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Base64
import com.google.gson.Gson
import domain.sahonmu.burger87.vo.announcement.Announcement
import domain.sahonmu.burger87.vo.store.Store


fun Any.encode(): String {
//    val gson = Gson()
//    val json = gson.toJson(this)
//    return URLEncoder.encode(json, "UTF-8")
    val json = Gson().toJson(this)
    return Base64.encodeToString(json.toByteArray(), Base64.URL_SAFE or Base64.NO_WRAP)
}

fun String.decode(): Any {
//    val decodedJson = URLDecoder.decode(this, "UTF-8")
//    return Gson().fromJson(decodedJson, Store::class.java)
    val json = String(Base64.decode(this, Base64.URL_SAFE or Base64.NO_WRAP))
    return Gson().fromJson(json, Store::class.java)
}


fun String.decodeAnnouncement(): Any {
//    val decodedJson = URLDecoder.decode(this, "UTF-8")
//    return Gson().fromJson(decodedJson, Store::class.java)
    val json = String(Base64.decode(this, Base64.URL_SAFE or Base64.NO_WRAP))
    return Gson().fromJson(json, Announcement::class.java)
}


//
fun String.decodeList(): Array<Store> {
//    val decodedJson = URLDecoder.decode(this, "UTF-8")
//    return Gson().fromJson(decodedJson, Store::class.java)
    val json = String(Base64.decode(this, Base64.URL_SAFE or Base64.NO_WRAP))
    return Gson().fromJson(json, Array<Store>::class.java)
}