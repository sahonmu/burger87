package data.sahonmu.burger87.mapper

import data.sahonmu.burger87.dto.StoreDto
import domain.sahonmu.burger87.entity.StoreEntity
import kotlin.String

fun StoreDto.toEntity(): StoreEntity = StoreEntity(
    id = this.id,
    created_at = this.created_at,
    address = this.address,
    branch = this.branch,
    city_code = this.city_code,
    city_name = this.city_name,
    description = this.description,
    latitude = this.latitude,
    longitude = this.longitude,
    instagram = this.instagram,
    name = this.name,
    tel = this.tel,
)