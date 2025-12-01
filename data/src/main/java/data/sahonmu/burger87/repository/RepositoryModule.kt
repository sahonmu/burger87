package data.sahonmu.burger87.repository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import domain.sahonmu.burger87.repository.StoreRepository
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

}