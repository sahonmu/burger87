package domain.sahonmu.burger87.repository.score

import domain.sahonmu.burger87.vo.score.ScoreInfo
import kotlinx.coroutines.flow.Flow

interface ScoreInfoRepository {
    fun scoreInfo(): Flow<List<ScoreInfo>>
}