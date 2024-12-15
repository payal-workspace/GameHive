package com.example.data.usecase

import com.example.core.common.di.IoDispatcher
import com.example.core.common.utils.Resource
import com.example.data.mapper.CategoriesDomainDataMapper
import com.example.data.repository.GameRepository
import com.example.domain.model.GamesModel
import com.example.domain.usecase.GetGameCategoriesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetGamesCategoriesUseCaseImp @Inject constructor(
    private val getDetailRepository: GameRepository,
    private val detailMapper: CategoriesDomainDataMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : GetGameCategoriesUseCase {

    override suspend operator fun invoke(): Flow<Resource<GamesModel>> = flow {
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