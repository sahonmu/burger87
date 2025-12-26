package com.sahonmu.burger87.extensions

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale


const val YEAR_MONTH_DAY = "yyyy-MM-dd"
const val YEAR_MONTH_DAY_NO_HYPHEN = "yyyyMMdd"

fun Long.toYearMonthDay(withHyphen: Boolean = true): String {
    return SimpleDateFormat(
        if (withHyphen) YEAR_MONTH_DAY else YEAR_MONTH_DAY_NO_HYPHEN,
        Locale.getDefault()
    ).format(this)
}

fun String.toYearMonthDay(withHyphen: Boolean = true): String {
    val date = OffsetDateTime.parse(this)
    return date.format(DateTimeFormatter.ofPattern(if(withHyphen) YEAR_MONTH_DAY else YEAR_MONTH_DAY_NO_HYPHEN))
}


fun Long.toYearMonthDay(): String {
    return Instant.ofEpochMilli(this)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
        .format(DateTimeFormatter.ofPattern(YEAR_MONTH_DAY_NO_HYPHEN))
}