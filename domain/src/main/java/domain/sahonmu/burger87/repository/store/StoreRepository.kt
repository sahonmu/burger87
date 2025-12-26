package domain.sahonmu.burger87.repository.store

import domain.sahonmu.burger87.vo.store.Store
import domain.sahonmu.burger87.vo.store.StoreImage
import domain.sahonmu.burger87.vo.store.StoreMenu
import kotlinx.coroutines.flow.Flow

interface StoreRepository {
    suspend fun createProduct(storeEntity: Store): Boolean
    suspend fun updateProduct(storeEntity: Store): Boolean
    suspend fun deleteProduct(id: String): Boolean
    fun getStoreList(): Flow<List<Store>>

    fun getStoreDetail(id: Long): Flow<Store>
    fun getStoreImageList(id: Long): Flow<List<StoreImage>>
    fun getStoreMenuList(id: Long): Flow<List<StoreMenu>>
}