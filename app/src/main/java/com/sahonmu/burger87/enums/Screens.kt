package com.sahonmu.burger87.enums


enum class Screens(var route: String) {
    SPLASH("SPLASH"),
    MAP("MAP"),
    STORE_DETAIL("STORE_DETAIL"),
    STORE_LIST("STORE_LIST")
}

//fun screens(route: String, id: Int): String {
//    return when(route) {
//        Screens.COMMUNITY_POST_DETAIL.route -> {
//            "${route}/${id}"
//        } else -> {
//            Screens.TAB_HOME.route
//        }
//    }
//}
