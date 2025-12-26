package com.sahonmu.burger87.enums

import com.sahonmu.burger87.extensions.toYearMonthDay

enum class EventState(val state: String) {
    SCHEDULED("예정"),
    PROGRESS("진행중"),
    FINISHED("종료")
}

fun checkSchedule(startDate: String, endDate: String): EventState {
    val startDate = startDate.toYearMonthDay(withHyphen = false).toInt()
    val currentDate = System.currentTimeMillis().toYearMonthDay().toInt()
    val endDate = endDate.toYearMonthDay(withHyphen = false).toInt()

    return if(currentDate > endDate) {
        EventState.FINISHED
    } else if(currentDate < startDate) {
        EventState.SCHEDULED
    } else {
        EventState.PROGRESS
    }
}