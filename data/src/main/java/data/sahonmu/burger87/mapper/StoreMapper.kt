package data.sahonmu.burger87.mapper

import data.sahonmu.burger87.dto.store.StoreDto
import data.sahonmu.burger87.dto.store.StoreImageDto
import domain.sahonmu.burger87.vo.store.Store
import domain.sahonmu.burger87.vo.store.StoreImage

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
    onTheWay = this.onTheWay
)
fun StoreImageDto.toDomain(): StoreImage = StoreImage(
    id = this.id,
    createdAt = this.createdAt,
    storeId = this.storeId,
    image = this.image,
)