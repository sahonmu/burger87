package domain.sahonmu.burger87.repository.app

import domain.sahonmu.burger87.vo.app.AppInfo
import kotlinx.coroutines.flow.Flow

interface AppInfoRepository {
    fun appInfo(): Flow<List<AppInfo>>
}