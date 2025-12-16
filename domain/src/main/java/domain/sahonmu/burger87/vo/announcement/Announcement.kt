package domain.sahonmu.burger87.vo.announcement

data class Announcement(
    val id: Long,
    val createdAt: String,
    val title: String,
    val contents: String,
    val image: String?,
    val storeId: Int?,
    val isHeader: Boolean
)