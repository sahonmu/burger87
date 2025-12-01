package domain.sahonmu.burger87.repository

import domain.sahonmu.burger87.entity.StoreEntity
import kotlinx.coroutines.flow.Flow

interface StoreRepository {
    suspend fun createProduct(storeEntity: StoreEntity): Boolean
    suspend fun updateProduct(storeEntity: StoreEntity): Boolean
    suspend fun deleteProduct(id: String): Boolean
    fun getStore(): Flow<List<StoreEntity>>
}