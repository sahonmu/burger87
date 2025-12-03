package domain.sahonmu.burger87.vo.store


data class StoreMenu(
    val id: Long,
    val storeId: Long,
    val name: String,
    val price: Int,
    val description: String,
    val image: String,
)