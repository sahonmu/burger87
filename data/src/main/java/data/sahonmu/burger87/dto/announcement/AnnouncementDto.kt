package data.sahonmu.burger87.dto.announcement

import android.annotation.SuppressLint
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class AnnouncementDto(
    @SerialName("id") val id: Long,
    @SerialName("created_at") val createdAt: String,
    @SerialName("title") val title: String,
    @SerialName("contents") val contents: String,
    @SerialName("image") val image: String?,
    @SerialName("store_id") val storeId: Long?,
    @SerialName("header") val isHeader: Boolean,
    @SerialName("link") val link: String?,
)