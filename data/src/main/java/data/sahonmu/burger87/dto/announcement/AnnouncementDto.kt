package data.sahonmu.burger87.dto.announcement

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnnouncementDto(
    @SerialName("id")val id: Long,
    @SerialName("created_at")val createdAt: String,
    @SerialName("title")val title: String,
    @SerialName("contents")val contents: String,
    @SerialName("image")val image: String?,

)