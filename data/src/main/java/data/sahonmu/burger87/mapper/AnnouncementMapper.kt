package data.sahonmu.burger87.mapper

import data.sahonmu.burger87.dto.announcement.AnnouncementDto
import domain.sahonmu.burger87.vo.announcement.Announcement

fun AnnouncementDto.toDomain(): Announcement = Announcement(
    id = this.id,
    createdAt = this.createdAt,
    title = this.title,
    contents = this.contents,
    image = this.image,
    storeId = this.storeId,
)
