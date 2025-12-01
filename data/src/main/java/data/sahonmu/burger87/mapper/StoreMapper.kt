package data.sahonmu.burger87.mapper

import data.sahonmu.burger87.dto.StoreDto
import domain.sahonmu.burger87.vo.Store

fun StoreDto.toDomain(): Store = Store(
    id = this.id,
    createdAt = this.createdAt,
    address = this.address,
    branch = this.branch,
    cityCode = this.cityCode,
    cityName = this.cityName,
    description = this.description,
    latitude = this.latitude,
    longitude = this.longitude,
    instagram = this.instagram,
    name = this.name,
    tel = this.tel,
    updateDate = this.updateDate,
    state = this.state
)