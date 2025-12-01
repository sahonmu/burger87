package data.sahonmu.burger87.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoreDto(

    @SerialName("id")val id: Long? = null,
    @SerialName("created_at")val created_at: String? = null, // timestamp with time zone → String으로 받는게 안전
    @SerialName("address")val address: String? = null,
    @SerialName("branch")val branch: String? = null,
    @SerialName("city_code")val city_code: String? = null,
    @SerialName("city_name")val city_name: String? = null,
    @SerialName("description")val description: String? = null,
    @SerialName("latitude")val latitude: Double? = 0.0,
    @SerialName("longitude")val longitude: Double? = 0.0,
    @SerialName("instagram")val instagram: String? = null,
    @SerialName("name")val name: String? = null,
    @SerialName("score")val score: String? = null,
    @SerialName("tel")val tel: String? = null
)
