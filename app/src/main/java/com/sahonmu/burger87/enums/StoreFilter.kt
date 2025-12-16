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
    BASIC(sortName = "기본 정렬"),
    CITY(sortName = "지역 정렬"),
    SCORE(sortName = "점수 정렬"),
    CHAR(sortName = "ABC 정렬"),
    VISIT_COUNT(sortName = "방문횟수 정렬")
}

fun sortMenu(sortName: String): SortMenu {
    return when (sortName) {
        SortMenu.CITY.sortName -> {
            SortMenu.CITY
        }

        SortMenu.SCORE.sortName -> {
            SortMenu.SCORE
        }

        SortMenu.CHAR.sortName -> {
            SortMenu.CHAR
        }

        SortMenu.VISIT_COUNT.sortName -> {
            SortMenu.VISIT_COUNT
        }

        else -> {
            SortMenu.BASIC
        }
    }
}