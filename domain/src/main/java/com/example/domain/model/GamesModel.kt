package com.example.domain.model

import androidx.annotation.DrawableRes
import com.example.core.common.model.DomainModel

data class GamesModel(val data : List<GamesModelData>?):DomainModel

data class GamesModelData(
    val name:String,
    @DrawableRes
    val gamePosterImage:Int,
    val items: List<GamesItemModel>?): DomainModel


data class GamesItemModel(
    val game_title: String
): DomainModel