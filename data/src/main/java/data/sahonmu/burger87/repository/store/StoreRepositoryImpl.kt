package data.sahonmu.burger87.repository.store

import android.util.Log
import data.sahonmu.burger87.dto.store.StoreDto
import data.sahonmu.burger87.dto.store.StoreImageDto
import data.sahonmu.burger87.dto.store.StoreMenuDto
import data.sahonmu.burger87.mapper.toDomain
import domain.sahonmu.burger87.repository.store.StoreRepository
import domain.sahonmu.burger87.vo.store.Store
import domain.sahonmu.burger87.vo.store.StoreMenu
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StoreRepositoryImpl(
    private val postgrest: Postgrest,
) : StoreRepository {
    override fun getStore() = flow {
        try {
            val response = postgrest["store"].select()
                .decodeList<StoreDto>()

            emit(response.map { it.toDomain() })

        } catch (e: Exception) {
            e.printStackTrace()
            emit(emptyList())
        }
    }


    override fun getStoreMenuList(id: Long) = flow {
        val response =
            postgrest["store_menu"].select { filter { eq("store_id", id) } }.decodeList<StoreMenuDto>()
        Log.i("list =", "${response.size}")
        emit(response.map { it.toDomain() })
    }

    override fun getStoreImageList(id: Long) = flow {
        val response =
            postgrest["store_image"].select { filter { eq("store_id", id) } }.decodeList<StoreImageDto>()
        emit(response.map { it.toDomain() })
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