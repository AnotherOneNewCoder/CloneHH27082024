package ru.zhogin.app.search.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import ru.zhogin.app.search.data.KtorRepImpl
import ru.zhogin.app.search.data.network.AppHttpClient
import ru.zhogin.app.search.domain.KtorRep
import ru.zhogin.app.search.domain.ServerReplyApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchFeatureModule {
    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) : OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    @Provides
    @Singleton
    fun providesServerReplyApi(okHttpClient: OkHttpClient?) : ServerReplyApi {
        return ServerReplyApi(
            baseUrl = BASE_URL,
            okHttpClient = okHttpClient,
        )
    }
    @Provides
    @Singleton
    fun providesHttpClient(httpClient: AppHttpClient): HttpClient = httpClient.getHttpClient()

    @Provides
    @Singleton
    fun providesKtorRepository(impl: KtorRepImpl) : KtorRep = impl
}

private const val BASE_URL = "https://drive.usercontent.google.com/"