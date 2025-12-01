package domain.sahonmu.burger87.repository

import domain.sahonmu.burger87.vo.Store
import kotlinx.coroutines.flow.Flow

interface StoreRepository {
    suspend fun createProduct(storeEntity: Store): Boolean
    suspend fun updateProduct(storeEntity: Store): Boolean
    suspend fun deleteProduct(id: String): Boolean
    fun getStore(): Flow<List<Store>>
}