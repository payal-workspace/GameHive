package com.example.data.model

import androidx.annotation.DrawableRes
import com.example.core.common.model.CategoriesEntity

data class SportsCategoryEntity(
    val data : List<SportsCategoryData>)
    : CategoriesEntity

data class SportsCategoryData(
    val sportsCategoryTitle:String,
    @DrawableRes
    val sportsCategoryImageUrl:Int,
    val sportsCategoryItem: List<SportsCategoryLists>?): CategoriesEntity


data class SportsCategoryLists(
    @DrawableRes
    val sportsImageUrl:Int,
    val sportsTitle: String,
    val sportsDescription: String
): CategoriesEntity