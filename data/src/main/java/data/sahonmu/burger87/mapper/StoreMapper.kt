package data.sahonmu.burger87.mapper

import data.sahonmu.burger87.dto.store.StoreDto
import data.sahonmu.burger87.dto.store.StoreImageDto
import data.sahonmu.burger87.dto.store.StoreMenuDto
import domain.sahonmu.burger87.vo.store.Store
import domain.sahonmu.burger87.vo.store.StoreImage
import domain.sahonmu.burger87.vo.store.StoreMenu

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
    state = this.state,
    thumbImage = this.thumbImage,
    score = this.score,
    cityFilter = this.cityFilter,
    visitCount = this.visitCount,
    lastVisitDate = this.lastVisitDate,
)
fun StoreImageDto.toDomain(): StoreImage = StoreImage(
    id = this.id,
    storeId = this.storeId,
    image = this.image,
)

fun StoreMenuDto.toDomain(): StoreMenu = StoreMenu(
    id = this.id,
    storeId = this.storeId,
    name = this.name,
    price = this.price,
    description = this.description,
    image = this.image,
)