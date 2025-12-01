package data.sahonmu.burger87.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import domain.sahonmu.burger87.repository.StoreRepository
import domain.sahonmu.burger87.usecase.store.StoreUseCase
import io.github.jan.supabase.SupabaseClient
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StoreApi {

    @Provides
    @Singleton
    fun provideGetProductUseCase(repository: StoreRepository): StoreUseCase =
        StoreUseCase(repository)

}

//@Singleton
//class StoreApi @Inject constructor(
//    private val supabaseClient: SupabaseClient
//) {
//    suspend fun getStores(): List<StoreUseCase> {
//        // TODO: Supabase에서 데이터 가져오는 로직
//        return listOf()
//    }
//}