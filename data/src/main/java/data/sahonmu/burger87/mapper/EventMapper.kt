package data.sahonmu.burger87.mapper

import data.sahonmu.burger87.dto.event.EventDto
import domain.sahonmu.burger87.vo.event.Event

fun EventDto.toDomain(): Event = Event(
    id = this.id,
    createdAt = this.createdAt,
    startDate = this.startDate,
    endDate = this.endDate,
    title = this.title,
    contents = this.contents,
    image = this.image,
    storeId = this.storeId,
    link = this.link
)
