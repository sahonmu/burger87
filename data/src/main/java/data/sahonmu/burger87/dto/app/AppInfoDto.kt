package data.sahonmu.burger87.dto.app

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppInfoDto(

    @SerialName("id")val id: Long,
    @SerialName("os")val os: String,
    @SerialName("app_version")val appVersion: String,
    @SerialName("force_update")val forceUpdate: Boolean,
    @SerialName("app_version_code")val appVersionCode: Int,

    )