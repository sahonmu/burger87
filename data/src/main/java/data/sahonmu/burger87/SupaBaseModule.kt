package data.sahonmu.burger87

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.storage.storage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SupaBaseModule {

    @Provides
    @Singleton
    fun provideSupaBaseClient(): SupabaseClient =
        createSupabaseClient(
            supabaseUrl = NetworkConstants.SUPABASE_API_URL,
            supabaseKey = NetworkConstants.SUPABASE_API_KEY
        ) {
            install(Postgrest)
//            install(Auth) {
//                flowType = FlowType.PKCE
//                scheme = "app"
//                host = "supabase.com"
//            }
//            install(GoTrue)
            install(Storage)
        }

    @Provides
    @Singleton
    fun provideSupaBaseDatabase(client: SupabaseClient): Postgrest = client.postgrest

//    @Provides
//    @Singleton
//    fun provideSupaBaseAuth(client: SupabaseClient): Auth = client.auth

    @Provides
    @Singleton
    fun provideSupaBaseStorage(client: SupabaseClient): Storage = client.storage


}