package com.sahonmu.burger87.enums


enum class Screens(var route: String) {
    SPLASH("SPLASH"),
    MAP("MAP"),
    STORE_DETAIL("STORE_DETAIL"),
    STORE_LIST("STORE_LIST"),
    STORE_SEARCH("STORE_SEARCH"),
    INFO("INFO"),
    BASED_ON_SCORE("INFO/BASED_ON_SCORE"),
    SHARING_EVENT_INFO("INFO/SHARING_EVENT_INFO"),
    PROVIDING_INFO("INFO/PROVIDING_INFO"),
    ANNOUNCEMENT_LIST("INFO/ANNOUNCEMENT_LIST"),
    ANNOUNCEMENT_DETAIL("INFO/ANNOUNCEMENT_DETAIL"),
    APP_VERSION("INFO/APP_VERSION"),


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
