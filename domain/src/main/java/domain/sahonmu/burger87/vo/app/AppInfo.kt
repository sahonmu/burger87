package domain.sahonmu.burger87.vo.app

data class AppInfo(
    val id: Long ,
    val os: String,
    val appVersion: String,
    val forceUpdate: Boolean,
    val appVersionCode: Int,
)