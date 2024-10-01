package ru.zhogin.app.search.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import ru.zhogin.app.search.data.database.VacanciesAndOffersDatabase
import ru.zhogin.app.search.data.network.AppHttpClient
import ru.zhogin.app.search.data.network.KtorRep
import ru.zhogin.app.search.data.repository.KtorRepImpl
import ru.zhogin.app.search.data.repository.OfferRepositoryImpl
import ru.zhogin.app.search.data.repository.VacancyRepositoryImpl
import ru.zhogin.app.search.domain.repository.OfferRepository
import ru.zhogin.app.search.domain.repository.VacancyRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchFeatureModule {

    @Provides
    @Singleton
    fun providesHttpClient(httpClient: AppHttpClient): HttpClient = httpClient.getHttpClient()

    @Provides
    @Singleton
    fun providesKtorRepository(impl: KtorRepImpl) : KtorRep = impl

    @Provides
    @Singleton
    fun providesVacanciesAndOffersDatabase(@ApplicationContext context: Context): VacanciesAndOffersDatabase {
        return VacanciesAndOffersDatabase(context)
    }

    @Provides
    @Singleton
    fun providesOfferRepository(impl: OfferRepositoryImpl) : OfferRepository = impl

    @Provides
    @Singleton
    fun providesVacancyRepository(impl: VacancyRepositoryImpl) : VacancyRepository = impl
}
