package domain.sahonmu.burger87.usecase.store

import domain.sahonmu.burger87.repository.store.StoreRepository
import domain.sahonmu.burger87.vo.store.Store
import domain.sahonmu.burger87.vo.store.StoreImage
import domain.sahonmu.burger87.vo.store.StoreMenu
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoreUseCase @Inject constructor(
    private val repository: StoreRepository
) {
    operator fun invoke(): Flow<List<Store>> = repository.getStoreList()
    operator fun invoke(id: Long): Flow<List<StoreImage>> = repository.getStoreImageList(id)
    fun storeMenuList(id: Long): Flow<List<StoreMenu>> = repository.getStoreMenuList(id)
    fun storeDetail(id: Long): Flow<Store> = repository.getStoreDetail(id)
}