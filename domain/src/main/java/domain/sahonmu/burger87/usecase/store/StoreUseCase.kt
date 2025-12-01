package domain.sahonmu.burger87.usecase.store

import domain.sahonmu.burger87.repository.StoreRepository
import domain.sahonmu.burger87.vo.Store
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoreUseCase @Inject constructor(
    private val repository: StoreRepository
) {
    operator fun invoke(): Flow<List<Store>> = repository.getStore()
}