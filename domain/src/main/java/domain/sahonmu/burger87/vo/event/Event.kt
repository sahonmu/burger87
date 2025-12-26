package domain.sahonmu.burger87.vo.event


data class Event(
    val id: Long,
    val createdAt: String,
    val startDate: String,
    val endDate: String,
    val title: String,
    val contents: String,
    val image: String?,
    val storeId: Long?,
    val link: String?
)