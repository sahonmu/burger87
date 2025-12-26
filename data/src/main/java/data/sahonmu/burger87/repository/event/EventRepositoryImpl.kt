package data.sahonmu.burger87.repository.event

import data.sahonmu.burger87.dto.event.EventDto
import data.sahonmu.burger87.mapper.toDomain
import domain.sahonmu.burger87.repository.event.EventRepository
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.flow.flow

class EventRepositoryImpl(
    private val postgrest: Postgrest,
) : EventRepository {

    override fun event() = flow {
        val response = postgrest["event"].select().decodeList<EventDto>()
        emit(response.map { it.toDomain() })
    }

    override fun event(id: Long) = flow {
        val response =
            postgrest["event"].select { filter { eq("id", id) } }.decodeList<EventDto>()
        emit(response.map { it.toDomain() })
    }
}