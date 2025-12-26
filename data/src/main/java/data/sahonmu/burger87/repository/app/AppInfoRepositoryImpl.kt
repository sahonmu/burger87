package data.sahonmu.burger87.repository.app

import data.sahonmu.burger87.dto.app.AppInfoDto
import data.sahonmu.burger87.mapper.toDomain
import domain.sahonmu.burger87.repository.app.AppInfoRepository
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.flow.flow

class AppInfoRepositoryImpl(
    private val postgrest: Postgrest,
) : AppInfoRepository {

    override fun appInfo() = flow {
        val response = postgrest["app_info"].select().decodeList<AppInfoDto>()
        emit(response.map { it.toDomain() })
    }

    override fun appInfo(os: String) = flow {
        val response =
            postgrest["app_info"].select { filter { eq("os", os) } }.decodeList<AppInfoDto>()
        emit(response.map { it.toDomain() })
    }


}