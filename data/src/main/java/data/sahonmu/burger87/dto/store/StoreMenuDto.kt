package data.sahonmu.burger87.dto.store

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoreMenuDto(
    @SerialName("id")val id: Long,
    @SerialName("store_id")val storeId: Long,
    @SerialName("name")val name: String,
    @SerialName("price")val price: Int,
    @SerialName("description")val description: String,
    @SerialName("image")val image: String,
)