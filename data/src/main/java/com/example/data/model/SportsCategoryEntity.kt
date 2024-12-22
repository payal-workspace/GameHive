package com.example.data.model

import androidx.annotation.DrawableRes
import com.example.core.common.model.CategoriesEntity

data class SportsCategoryEntity(
    val data : List<SportsCategoryData>)
: CategoriesEntity

data class SportsCategoryData(
    val sports_type:String,
    @DrawableRes
    val img_sports_category:Int,
    val sports_type_items: List<SportsCategoryLists>?): CategoriesEntity


data class SportsCategoryLists(
    @DrawableRes
    val img_sports_type_item:Int,
    val sports_types_games: String
): CategoriesEntity