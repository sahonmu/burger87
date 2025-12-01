package domain.sahonmu.burger87.entity

data class StoreEntity(
    val id: Long?,
    val created_at: String? = null, // timestamp with time zone → String으로 받는게 안전
    val address: String?,
    val branch: String?,
    val city_code: String?,
    val city_name: String?,
    val description: String?,
    val latitude: Double?,
    val longitude: Double?,
    val instagram:  String?,
    val name: String?,
    val tel: String?
)
