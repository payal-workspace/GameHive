package com.example.data.mapper

import com.example.core.common.extensions.DataToDomainModelMapper
import com.example.data.model.SportsCategoryData
import com.example.data.model.SportsCategoryEntity
import com.example.data.model.SportsCategoryLists
import com.example.domain.model.SportsModelLists
import com.example.domain.model.SportsModel
import com.example.domain.model.SportsModelData
import javax.inject.Inject

class CategoriesDomainDataMapper @Inject constructor() :
    DataToDomainModelMapper<SportsCategoryEntity, SportsModel> {
    override fun mapToDomainModel(responseModel: SportsCategoryEntity?): SportsModel {
        val detailResponse = requireNotNull(responseModel)
        return SportsModel(
            data = dataToDomainModel(detailResponse.data),

        )
    }

    private fun dataToDomainModel(gameTitles: List<SportsCategoryData>?): List<SportsModelData>? {
        return gameTitles?.map {
            SportsModelData(
                name = it.sports_type,
                gamePosterImage = it.img_sports_category ,
                items = dataToDomainModelItem(it.sports_type_items)
            )
        } ?: emptyList()
    }

    private fun dataToDomainModelItem(gameTitles: List<SportsCategoryLists>?): List<SportsModelLists>? {
        return gameTitles?.map {
            SportsModelLists(
                img_sports_type_item = it.img_sports_type_item,
                game_title = it.sports_types_games
            )
        } ?: emptyList()
    }

}

