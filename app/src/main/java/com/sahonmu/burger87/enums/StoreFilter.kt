package com.sahonmu.burger87.enums

enum class StoreSort {
    BASIC,
    SORT,
    SORT_DESC,
}

enum class ScoreSort {
    SORT,
    SORT_DESC
}

enum class ClosedFilter {
    ALL,
    OPERATION,
    CLOSED
}

enum class SortMenu(var sortName: String) {
    BASIC(sortName = "기본"),
    DISTANCE(sortName = "거리"),
    CITY(sortName = "지역"),
    SCORE(sortName = "별점"),
    VISIT_COUNT(sortName = "방문횟수")

}

fun sortMenu(sortName: String): SortMenu {
    return when (sortName) {
        SortMenu.CITY.sortName -> {
            SortMenu.CITY
        }

        SortMenu.SCORE.sortName -> {
            SortMenu.SCORE
        }

        SortMenu.VISIT_COUNT.sortName -> {
            SortMenu.VISIT_COUNT
        }

        SortMenu.DISTANCE.sortName -> {
            SortMenu.DISTANCE
        }

        else -> {
            SortMenu.BASIC
        }
    }
}