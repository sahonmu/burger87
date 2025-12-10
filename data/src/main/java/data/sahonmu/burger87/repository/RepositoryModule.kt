package data.sahonmu.burger87.repository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import data.sahonmu.burger87.repository.app.AppInfoRepositoryImpl
import data.sahonmu.burger87.repository.score.ScoreInfoRepositoryImpl
import data.sahonmu.burger87.repository.store.StoreRepositoryImpl
import domain.sahonmu.burger87.repository.app.AppInfoRepository
import domain.sahonmu.burger87.repository.score.ScoreInfoRepository
import domain.sahonmu.burger87.repository.store.StoreRepository
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Provides
    @Singleton
    fun provideStoreRepository(
        postgrest: Postgrest
    ): StoreRepository = StoreRepositoryImpl(postgrest)

    @Provides
    @Singleton
    fun provideAppInfoRepository(
        postgrest: Postgrest
    ): AppInfoRepository = AppInfoRepositoryImpl(postgrest)


    @Provides
    @Singleton
    fun provideScoreInfoRepository(
        postgrest: Postgrest
    ): ScoreInfoRepository = ScoreInfoRepositoryImpl(postgrest)

}