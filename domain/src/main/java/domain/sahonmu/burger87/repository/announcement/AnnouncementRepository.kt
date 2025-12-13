package domain.sahonmu.burger87.repository.announcement

import domain.sahonmu.burger87.vo.announcement.Announcement
import kotlinx.coroutines.flow.Flow

interface AnnouncementRepository {
    fun announcementList(): Flow<List<Announcement>>
}