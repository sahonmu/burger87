package com.sahonmu.burger87.enums


enum class Screens(var route: String) {
    SPLASH("SPLASH"),
    MAP("MAP"),
    STORE_DETAIL("STORE_DETAIL"),
    STORE_LIST("STORE_LIST"),
    STORE_SEARCH("STORE_SEARCH"),
    INFO("INFO"),
    ANNOUNCEMENT_LIST("ANNOUNCEMENT_LIST"),
    ANNOUNCEMENT_DETAIL("ANNOUNCEMENT_DETAIL"),
    APP_VERSION("APP_VERSION"),
    SCORE_CRITERIA("SCORE_CRITERIA"),

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
