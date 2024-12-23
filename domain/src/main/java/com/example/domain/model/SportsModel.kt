package com.example.domain.model

import androidx.annotation.DrawableRes
import com.example.core.common.model.DomainModel


data class SportsModel(val data : List<SportsModelData>?):DomainModel

data class SportsModelData(
    val sportsCategoryTitle:String,
    @DrawableRes
    val sportsCategoryImageUrl:Int,
    val sportsCategoryItem: List<SportsModelLists>?): DomainModel


data class SportsModelLists(
    @DrawableRes
    val sportsImageUrl:Int,
    val sportsTitle: String,
    val sportsDescription: String
): DomainModel