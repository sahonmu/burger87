package data.sahonmu.burger87.repository.score

import data.sahonmu.burger87.dto.score.ScoreInfoDto
import data.sahonmu.burger87.mapper.toDomain
import domain.sahonmu.burger87.repository.score.ScoreInfoRepository
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.flow.flow

class ScoreInfoRepositoryImpl(
    private val postgrest: Postgrest,
) : ScoreInfoRepository {

    override fun scoreInfo() = flow {
//        try {
            val response = postgrest["score_info"].select().decodeList<ScoreInfoDto>()
            emit(response.map { it.toDomain() })
//        } catch (e: Exception) {
//
//        }

    }
}