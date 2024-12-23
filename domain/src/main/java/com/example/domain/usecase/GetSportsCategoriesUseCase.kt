package com.example.domain.usecase

import com.example.core.common.utils.Resource
import com.example.domain.model.SportsModel
import kotlinx.coroutines.flow.Flow

interface GetSportsCategoriesUseCase {
    suspend operator fun invoke(): Flow<Resource<SportsModel>>
}
