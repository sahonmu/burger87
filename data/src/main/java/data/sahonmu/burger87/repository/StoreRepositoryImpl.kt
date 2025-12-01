package data.sahonmu.burger87.repository

import android.util.Log
import data.sahonmu.burger87.dto.StoreDto
import data.sahonmu.burger87.mapper.toDomain
import domain.sahonmu.burger87.repository.StoreRepository
import domain.sahonmu.burger87.vo.Store
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.flow.flow

class StoreRepositoryImpl(
    private val postgrest: Postgrest,
) : StoreRepository {
    override fun getStore() = flow {
        try {
            Log.i("SAHONMU", "252525 SELECT == ${postgrest["store"].select()}")
            Log.i("SAHONMU", "252525 SELECT DECODE == ${postgrest["store"].select().decodeList<StoreDto>()}")
            val response = postgrest["store"].select()
                .decodeList<StoreDto>()

            emit(response.map { it.toDomain() })

        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("SAHONMU", "252525 ERROR == ${e}")
            emit(emptyList())
        }
    }

    override suspend fun deleteProduct(id: String): Boolean {
        return false
    }

    override suspend fun updateProduct(store: Store): Boolean {
        return false
    }

    override suspend fun createProduct(store: Store): Boolean {
        return false
    }
}