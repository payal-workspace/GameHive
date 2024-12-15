package com.example.core.common.extensions

import com.example.core.common.model.CategoriesModel
import com.example.core.common.model.DomainModel

interface DataToDomainModelMapper<R: CategoriesModel, D: DomainModel> {
    fun mapToDomainModel(responseModel: R?): D?
}