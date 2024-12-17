package com.example.data.model

import androidx.annotation.DrawableRes
import com.example.core.common.model.CategoriesModel

data class GamesResponse(
    val data : List<GamesResponseData>)
: CategoriesModel

data class GamesResponseData(
    val name:String,
    @DrawableRes
    val gamePosterImage:Int,
    val items: List<GamesResponseItem>?): CategoriesModel


data class GamesResponseItem(
    val game_title: String
): CategoriesModel