package domain.sahonmu.burger87.usecase.app

import domain.sahonmu.burger87.repository.app.AppInfoRepository
import domain.sahonmu.burger87.vo.app.AppInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppInfoUseCase @Inject constructor(
    private val repository: AppInfoRepository
) {
    operator fun invoke(): Flow<List<AppInfo>> = repository.appInfo()
    operator fun invoke(os: String): Flow<List<AppInfo>> = repository.appInfo(os)
}