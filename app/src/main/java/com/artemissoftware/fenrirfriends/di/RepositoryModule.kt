package com.artemissoftware.fenrirfriends.di

import com.artemissoftware.data.remote.source.DogApiSource
import com.artemissoftware.data.repositories.BreedRepositoryImpl
import com.artemissoftware.domain.repositories.BreedRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideBreedRepository(dogApiSource: DogApiSource): BreedRepository {
        return BreedRepositoryImpl(dogApiSource)
    }
}