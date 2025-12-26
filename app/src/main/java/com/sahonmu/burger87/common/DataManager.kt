package com.sahonmu.burger87.common

import domain.sahonmu.burger87.vo.store.Store

object DataManager {

    fun store(): Store {
        return Store(
            id = 1,
            createdAt = "",
            address = "서울시 서대문구 마포인좌동\n서울시 서대문구 마포인좌동",
            branch = "서울점",
            cityCode = "SEOUL",
            cityName = "서울",
            description = "디스크립션",
            latitude = 0.0,
            longitude = 0.0,
            instagram = "instasd",
            name = "클래식 햄버거\n클래식 햄버거",
            tel = "02-1004-1004",
            updateDate = "",
            state = "OPERATION",
            thumbImage = "",
            score = 4.0f,
            cityFilter = "전체",
            lastVisitDate = "",
            visitCount = 1,
            regionType = "DOMESTIC"
        ).apply {
            distance = 9999.0
        }
    }
}