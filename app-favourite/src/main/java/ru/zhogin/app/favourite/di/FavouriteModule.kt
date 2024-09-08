package ru.zhogin.app.favourite.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.zhogin.app.favourite.data.FavouriteRepositoryImpl
import ru.zhogin.app.favourite.domain.repository.FavouriteRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavouriteModule {
    @Provides
    @Singleton
    fun providesFavouriteRepository(impl: FavouriteRepositoryImpl) : FavouriteRepository = impl
}