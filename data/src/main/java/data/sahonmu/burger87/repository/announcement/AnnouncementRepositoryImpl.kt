package data.sahonmu.burger87.repository.announcement

import data.sahonmu.burger87.dto.announcement.AnnouncementDto
import data.sahonmu.burger87.mapper.toDomain
import domain.sahonmu.burger87.repository.announcement.AnnouncementRepository
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.flow.flow

class AnnouncementRepositoryImpl(
    private val postgrest: Postgrest,
) : AnnouncementRepository {

    override fun announcementList() = flow {
//        try {
            val response = postgrest["announcement"].select().decodeList<AnnouncementDto>()
            emit(response.map { it.toDomain() })
//        } catch (e: Exception) {
//
//        }

    }
}