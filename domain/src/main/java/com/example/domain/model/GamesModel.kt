package com.example.domain.model

import com.example.core.common.model.DomainModel

data class GamesModel(val items: List<GamesItemModel>?): DomainModel


data class GamesItemModel(
    val name: String,
    val description: String
): DomainModel