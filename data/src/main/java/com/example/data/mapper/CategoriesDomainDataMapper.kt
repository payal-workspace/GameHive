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
        val detailResponse = responseModel ?: return GamesModel(items = emptyList())
        return GamesModel(
            items = dataProductsToDomainProducts(detailResponse.items)
        )
    }

    private fun dataProductsToDomainProducts(otherProducts: List<GamesResponseItem>?): List<GamesItemModel>? {
        return otherProducts?.map {
            GamesItemModel(
                name = it.name,
                description = it.description
            )
        } ?: emptyList()
    }

}