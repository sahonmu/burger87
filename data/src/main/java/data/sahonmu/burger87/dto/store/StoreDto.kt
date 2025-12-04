package data.sahonmu.burger87.dto.store

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoreDto(

    @SerialName("id")val id: Long,
    @SerialName("created_at")val createdAt: String, // timestamp with time zone → String으로 받는게 안전
    @SerialName("address")val address: String,
    @SerialName("branch")val branch: String,
    @SerialName("city_code")val cityCode: String,
    @SerialName("city_name")val cityName: String,
    @SerialName("description")val description: String,
    @SerialName("latitude")val latitude: Double,
    @SerialName("longitude")val longitude: Double,
    @SerialName("instagram")val instagram: String,
    @SerialName("name")val name: String,
    @SerialName("score")val score: Float,
    @SerialName("tel")val tel: String,
    @SerialName("update_date")val updateDate: String? = null,
    @SerialName("state")val state: String,
    @SerialName("thumb_image")val thumbImage: String,
    @SerialName("on_the_way")val onTheWay: String,
    @SerialName("city_filter")val cityFilter: String,


)