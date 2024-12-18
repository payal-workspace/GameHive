package com.example.data.usecase

import com.example.core.common.di.IoDispatcher
import com.example.core.common.utils.Resource
import com.example.data.mapper.CategoriesDomainDataMapper
import com.example.data.repository.GameRepository
import com.example.domain.model.SportsModel
import com.example.domain.usecase.GetSportsCategoriesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetSportsCategoriesUseCaseImp @Inject constructor(
    private val getDetailRepository: GameRepository,
    private val detailMapper: CategoriesDomainDataMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : GetSportsCategoriesUseCase {

    override suspend operator fun invoke(): Flow<Resource<SportsModel>> = flow {
        try {
            when (val resource = getDetailRepository.getDetail()) {
                Resource.Loading -> {
                    emit(Resource.Loading)
                }
                is Resource.Success -> {
                    val mappedData = detailMapper.mapToDomainModel(resource.data)
                    emit(Resource.Success(mappedData))
                }
                is Resource.Failure -> {
                    emit(Resource.Failure(resource.error))
                }

            }
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }.flowOn(dispatcher)
}