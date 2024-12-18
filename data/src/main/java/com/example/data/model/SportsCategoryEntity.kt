package com.example.data.model

import androidx.annotation.DrawableRes
import com.example.core.common.model.CategoriesEntity

data class SportsCategoryEntity(
    val data : List<SportsCategoryData>)
: CategoriesEntity

data class SportsCategoryData(
    val sports_type:String,
    @DrawableRes
    val sports_type_image:Int,
    val sports_type_items: List<SportsCategoryLists>?): CategoriesEntity


data class SportsCategoryLists(
    val sports_types_games: String
): CategoriesEntity