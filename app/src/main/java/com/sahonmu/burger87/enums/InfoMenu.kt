package com.sahonmu.burger87.enums

import com.sahonmu.burger87.R

enum class InfoMenu(val menuName: String, val menuResource: Int) {
    INSTAGRAM("버거87의 인스타그램", R.drawable.ic_icon_instagram),
    ANNOUNCEMENT("공지사항", R.drawable.ic_icon_announcement),
    REPORT("버거추천", R.drawable.ic_icon_report_burger),
    OPEN_SOURCE("오픈소스", R.drawable.ic_icon_open_source),
    VERSION_INFO("버전정보", R.drawable.ic_icon_information),
}