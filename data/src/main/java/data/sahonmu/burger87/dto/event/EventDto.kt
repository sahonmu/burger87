package data.sahonmu.burger87.dto.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EventDto(
    @SerialName("id")val id: Long,
    @SerialName("created_at")val createdAt: String,
    @SerialName("start_date")val startDate: String,
    @SerialName("end_date")val endDate: String,
    @SerialName("title")val title: String,
    @SerialName("contents")val contents: String,
    @SerialName("image")val image: String?,
    @SerialName("store_id")val storeId: Long?,
)