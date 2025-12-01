package data.sahonmu.burger87


object  NetworkConstants {

    enum class SERVER(var title: String) {
        DEV("dev"),
        LIVE("live"),
    }

    val SUPABASE_API_URL = "https://qgdnsxoaozeipuxcltka.supabase.co"
    val SUPABASE_API_KEY = "sb_publishable_mxRTEpx_4uwsRvUjU0w1JQ_3NS5Jzbe"
//    val SUPABASE_API_KEY = "sb_publishable_mxRTEpx_4uwsRvUjU0w1JQ_3NS5Jzbe"
//    val SUPABASE_API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJliZI6InFnZG5zeG9hb3plaXB1eGNsdGthIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjQwNzMwNjQsImV4cCI6MjA3OTY0OTA2NH0.QIJbtm40ZA_0mdpdtugf4uf1nHf1tNtnM7tqXk4USDk"
//    val SUPABASE_API_URL = when(BuildConfig.FLAVOR) {
//        SERVER.LIVE.title -> {
//            "https://qgdnsxoaozeipuxcltka.supabase.co"
//        }
//        else -> {
//            "https://qgdnsxoaozeipuxcltka.supabase.co"
//        }
//    }
//    val SUPABASE_API_KEY = when(BuildConfig.FLAVOR) {
//        SERVER.LIVE.title -> {
//            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InFnZG5zeG9hb3plaXB1eGNsdGthIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjQwNzMwNjQsImV4cCI6MjA3OTY0OTA2NH0.QIJbtm40ZA_0mdpdtugf4uf1nHf1tNtnM7tqXk4USDk"
//        }
//        else -> {
//            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InFnZG5zeG9hb3plaXB1eGNsdGthIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjQwNzMwNjQsImV4cCI6MjA3OTY0OTA2NH0.QIJbtm40ZA_0mdpdtugf4uf1nHf1tNtnM7tqXk4USDk"
//        }
//    }


    const val CONNECT_TIMEOUT = 30L
    const val WRITE_TIMEOUT = 30L
    const val READ_TIMEOUT = 30L

}