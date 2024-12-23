package com.example.data.di

import com.example.data.dataSource.SportsTabel
import com.example.data.repository.GameRepository
import com.example.data.repository.GameRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideHomeRepository(
        dataSource: SportsTabel
    ): GameRepository = GameRepositoryImpl(
        dataSource
    )
}