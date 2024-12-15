package com.example.domain.usecase

import com.example.core.common.utils.Resource
import com.example.domain.model.GamesModel
import kotlinx.coroutines.flow.Flow

interface GetGameCategoriesUseCase {
    suspend operator fun invoke(): Flow<Resource<GamesModel>>
}
