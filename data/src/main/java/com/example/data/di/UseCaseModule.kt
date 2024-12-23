package com.example.data.di

import com.example.core.common.di.IoDispatcher
import com.example.data.mapper.CategoriesDomainDataMapper
import com.example.data.repository.GameRepository
import com.example.data.usecase.GetSportsCategoriesUseCaseImp
import com.example.domain.usecase.GetSportsCategoriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideHomeUseCase(
        gameRepository: GameRepository,
        categoriesToDomainDataMapper: CategoriesDomainDataMapper,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): GetSportsCategoriesUseCase = GetSportsCategoriesUseCaseImp(
        gameRepository,
        categoriesToDomainDataMapper,
        dispatcher
    )
}