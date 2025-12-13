package domain.sahonmu.burger87.repository.event

import domain.sahonmu.burger87.vo.event.Event
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    fun event(): Flow<List<Event>>
    fun event(id: Long): Flow<List<Event>>
}