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
    CHAR(sortName = "ABC 정렬")
}