package domain.sahonmu.burger87.usecase.score

import domain.sahonmu.burger87.repository.score.ScoreInfoRepository
import domain.sahonmu.burger87.vo.score.ScoreInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScoreInfoUseCase @Inject constructor(
    private val repository: ScoreInfoRepository
) {
    operator fun invoke(): Flow<List<ScoreInfo>> = repository.scoreInfo()
}