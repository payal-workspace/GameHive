package com.example.data.dataSource

import com.example.data.R
import com.example.data.model.GamesResponse
import com.example.data.model.GamesResponseItem


class GamesDataStoreImpl {

    private val gameListsData =
        GamesResponse(
            "Sci-Fi", R.drawable.baking_powder, items =
            listOf(
                GamesResponseItem("Description 3"),
                GamesResponseItem("Description 4"),
                GamesResponseItem("Description 4"),
                GamesResponseItem("Description 4"),
                GamesResponseItem("Description 4"),
                GamesResponseItem("Description 4"),
                GamesResponseItem("Description 4"),
                GamesResponseItem("Description 4"),
                GamesResponseItem("Description 4"),
                GamesResponseItem("Description 4"),
                GamesResponseItem("Description 4"),
                GamesResponseItem("Description 4"),
                GamesResponseItem("Description 4"),
                GamesResponseItem("Description 4"),
                GamesResponseItem("Description 4"),
                GamesResponseItem("Description 4"),
                GamesResponseItem("Description 4"),
            )

        )

    suspend fun getGameListsData(): GamesResponse {
        return gameListsData
    }

}