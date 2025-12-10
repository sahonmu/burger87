package domain.sahonmu.burger87.usecase.announcement

import domain.sahonmu.burger87.repository.announcement.AnnouncementRepository
import domain.sahonmu.burger87.vo.announcement.Announcement
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnnouncementUseCase @Inject constructor(
    private val repository: AnnouncementRepository
) {
    operator fun invoke(): Flow<List<Announcement>> = repository.announcementList()
}