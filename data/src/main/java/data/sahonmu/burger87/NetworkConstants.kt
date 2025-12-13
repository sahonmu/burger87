package data.sahonmu.burger87


object  NetworkConstants {

    enum class SERVER(var title: String) {
        DEV("dev"),
        LIVE("live"),
    }

    val SUPABASE_API_URL = when(BuildConfig.FLAVOR) {
        SERVER.LIVE.title -> {
            "https://qgdnsxoaozeipuxcltka.supabase.co"
        }
        else -> {
            "https://qgdnsxoaozeipuxcltka.supabase.co"
        }
    }

    val SUPABASE_API_KEY = when(BuildConfig.FLAVOR) {
        SERVER.LIVE.title -> {
            "sb_publishable_mxRTEpx_4uwsRvUjU0w1JQ_3NS5Jzbe"
        }
        else -> {
            "sb_publishable_mxRTEpx_4uwsRvUjU0w1JQ_3NS5Jzbe"
        }
    }

    const val CONNECT_TIMEOUT = 30L
    const val WRITE_TIMEOUT = 30L
    const val READ_TIMEOUT = 30L

}