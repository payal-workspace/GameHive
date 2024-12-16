package com.example.data.di

import com.example.core.common.di.IoDispatcher
import com.example.data.mapper.CategoriesDomainDataMapper
import com.example.data.repository.GameRepository
import com.example.data.usecase.GetGamesCategoriesUseCaseImp
import com.example.domain.usecase.GetGameCategoriesUseCase
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
    ): GetGameCategoriesUseCase = GetGamesCategoriesUseCaseImp(
        gameRepository,
        categoriesToDomainDataMapper,
        dispatcher
    )
}