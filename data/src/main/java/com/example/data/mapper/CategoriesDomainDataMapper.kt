package com.example.data.mapper

import com.example.core.common.extensions.DataToDomainModelMapper
import com.example.data.model.GamesResponse
import com.example.data.model.GamesResponseItem
import com.example.domain.model.GamesItemModel
import com.example.domain.model.GamesModel
import javax.inject.Inject

class CategoriesDomainDataMapper @Inject constructor() :
    DataToDomainModelMapper<GamesResponse, GamesModel> {
    override fun mapToDomainModel(responseModel: GamesResponse?): GamesModel {
        val detailResponse = requireNotNull(responseModel)
        return GamesModel(
            name = responseModel.name,
            gamePosterImage = responseModel.gamePosterImage,
            items = dataToDomainModelItem(detailResponse.items)
        )
    }

    private fun dataToDomainModelItem(gameTitles: List<GamesResponseItem>?): List<GamesItemModel>? {
        return gameTitles?.map {
            GamesItemModel(
                game_title = it.game_title
            )
        } ?: emptyList()
    }

}