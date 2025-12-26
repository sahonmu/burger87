package data.sahonmu.burger87.mapper

import data.sahonmu.burger87.dto.app.AppInfoDto
import data.sahonmu.burger87.dto.store.StoreDto
import domain.sahonmu.burger87.vo.app.AppInfo
import domain.sahonmu.burger87.vo.store.Store

fun AppInfoDto.toDomain(): AppInfo = AppInfo(
    id = this.id,
    os = this.os,
    appVersion = this.appVersion,
    forceUpdate = this.forceUpdate,
    appVersionCode = this.appVersionCode,
)