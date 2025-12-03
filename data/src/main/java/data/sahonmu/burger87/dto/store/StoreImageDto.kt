package data.sahonmu.burger87.dto.store

import android.annotation.SuppressLint
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class StoreImageDto(
    @SerialName("id")val id: Long,
    @SerialName("created_at")val createdAt: String,
    @SerialName("store_id")val storeId: Long,
    @SerialName("image")val image: String,
)