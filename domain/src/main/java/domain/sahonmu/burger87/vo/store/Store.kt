package domain.sahonmu.burger87.vo.store

import domain.sahonmu.burger87.enums.storeState

data class Store(
    val id: Long,
    val createdAt: String, // timestamp with time zone → String으로 받는게 안전
    val address: String,
    val branch: String,
    val cityCode: String,
    val cityName: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val instagram:  String,
    val name: String,
    val tel: String,
    val updateDate: String? = null,
    val state: String,
    val thumbImage: String,
    val score: Float,
    val cityFilter: String,
    val visitCount: Int,
    val lastVisitDate: String,
    val regionType: String
) {
    var storeState = state.storeState()
    var startIndex = 0
    var endIndex = 0
    var fullName = if(branch.isEmpty()) name else "${name}(${branch})"
    var isMatch = startIndex + endIndex != 0

    fun isKeywordMatch(startIndex: Int, endIndex: Int): Boolean {
        return startIndex + endIndex != 0
    }

    fun isKeywordMatch(): Boolean {
        return startIndex + endIndex != 0
    }

    fun isDomestic(): Boolean {
        return regionType == "DOMESTIC"
    }

}