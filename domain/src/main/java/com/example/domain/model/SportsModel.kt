package com.example.domain.model

import androidx.annotation.DrawableRes
import com.example.core.common.model.DomainModel

data class SportsModel(val data : List<SportsModelData>?):DomainModel

data class SportsModelData(
    val name:String,
    @DrawableRes
    val gamePosterImage:Int,
    val items: List<SportsModelLists>?): DomainModel


data class SportsModelLists(
    @DrawableRes
    val img_sports_type_item:Int,
    val game_title: String
): DomainModel