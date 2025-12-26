package domain.sahonmu.burger87.usecase.event

import domain.sahonmu.burger87.repository.event.EventRepository
import domain.sahonmu.burger87.vo.event.Event
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventUseCase @Inject constructor(
    private val repository: EventRepository
) {
    operator fun invoke(): Flow<List<Event>> = repository.event()
}