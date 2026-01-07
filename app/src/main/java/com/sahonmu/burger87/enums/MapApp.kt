package com.sahonmu.burger87.enums

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.sahonmu.burger87.utils.AppUtils

enum class MapApp(val packageName: String, val appName: String) {
    GOOGLE("com.google.android.apps.maps", "구글지도"),
    KAKAO("net.daum.android.map", "카카오지도"),
    NAVER("com.nhn.android.nmap", "네이버지도"),
    TMAP("com.skt.tmap.ku", "티맵")
}

enum class RouteType {
    Transportation,
    Car

}

fun getInstalledMapApps(context: Context): List<MapApp> {
    return MapApp.entries.filter {
        AppUtils.isAppInstalled(context, it.packageName)
    }
}


fun openRoute(
    context: Context,
    routeType: RouteType,
    app: MapApp,
    lat: Double,
    lng: Double,
    name: String
) {
    val isTransportation = routeType == RouteType.Transportation
    val (uri, pkg) = when (app) {
        MapApp.GOOGLE ->
            if(isTransportation) {
                "google.navigation:q=$lat,$lng&mode=transit" to "com.google.android.apps.maps"
            } else {
                "google.navigation:q=$lat,$lng&mode=d" to "com.google.android.apps.maps"
            }

        MapApp.KAKAO ->
                if(isTransportation) {
                    "kakaomap://route?ep=$lat,$lng&by=PUBLICTRANSIT&name=$name" to "net.daum.android.map"
                } else {
                    "kakaomap://route?ep=$lat,$lng&by=CAR&name=$name" to "net.daum.android.map"
                }

        MapApp.NAVER ->
                if(isTransportation) {
                    "nmap://route/public?dlat=$lat&dlng=$lng&dname=$name" to "com.nhn.android.nmap"
                } else {
                    "nmap://route/car?dlat=$lat&dlng=$lng&dname=$name" to "com.nhn.android.nmap"
                }

        MapApp.TMAP ->
            "tmap://route?goalx=$lng&goaly=$lat&goalname=$name" to "com.skt.tmap.ku"
    }

    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri)).apply {
        setPackage(pkg)
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    context.startActivity(intent)
}