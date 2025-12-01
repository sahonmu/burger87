package data.sahonmu.burger87.repository

import android.util.Log
import dagger.hilt.android.scopes.ActivityRetainedScoped
import data.sahonmu.burger87.dto.StoreDto
import data.sahonmu.burger87.mapper.toEntity
import domain.sahonmu.burger87.entity.StoreEntity
import domain.sahonmu.burger87.repository.StoreRepository
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoreRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest,
    private val storage: Storage,
) : StoreRepository {
    override fun getStore() = flow {
        try {
            Log.i("SAHONMU", "252525 SELECT == ${postgrest["store"].select()}")
            Log.i("SAHONMU", "252525 SELECT DECODE == ${postgrest["store"].select().decodeList<StoreDto>()}")
            val response = postgrest["store"].select()
                .decodeList<StoreDto>()

            emit(response.map { it.toEntity() })

        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("SAHONMU", "252525 ERROR == ${e}")
            emit(emptyList())
        }
    }

    override suspend fun deleteProduct(id: String): Boolean {
        return false
    }

    override suspend fun updateProduct(storeEntity: StoreEntity): Boolean {
        return false
    }

    override suspend fun createProduct(storeEntity: StoreEntity): Boolean {
        return false
    }
}