package com.example.data.repository

import com.example.core.common.utils.Resource
import com.example.data.dataSource.SportsTable
import com.example.data.model.SportsCategoryEntity
import java.io.IOException
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val gameDataSource: SportsTable
) : GameRepository {

    override suspend fun getDetail(): Resource<SportsCategoryEntity> {
        return try {
            val response = gameDataSource.getGameListsData()
            Resource.Success(response)
        } catch (e: IOException) {
            Resource.Failure(e)
        }
    }

}
