package com.example.data.model

import com.example.core.common.model.CategoriesModel

data class GamesResponse ( val items: List<GamesResponseItem>?): CategoriesModel


data class GamesResponseItem(
    val name: String,
    val description: String
): CategoriesModel