package com.example.data.repository

import com.example.core.common.utils.Resource
import com.example.data.dataSource.GamesDataStoreImpl
import com.example.data.model.GamesResponse
import java.io.IOException
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val gameDataSource: GamesDataStoreImpl
) : GameRepository {

    override suspend fun getDetail(): Resource<GamesResponse> {
        return try {
            val response = gameDataSource.getGameListsData()
            Resource.Success(response)
        } catch (e: IOException) {
            Resource.Failure(e)
        }
    }

}
