package com.example.data.dataSource

import com.example.data.model.GamesResponse
import com.example.data.model.GamesResponseItem
import javax.inject.Inject


class GamesDataStoreImpl  {

    private val gameListsData = GamesResponse(
        listOf(
            GamesResponseItem("Item 3", "Description 3"),
            GamesResponseItem("Item 4", "Description 4")
        )
    )
    suspend fun getListFirst(): GamesResponse {
        return gameListsData
    }

}