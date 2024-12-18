package com.example.core.common.extensions

import com.example.core.common.model.CategoriesEntity
import com.example.core.common.model.DomainModel

interface DataToDomainModelMapper<R: CategoriesEntity, D: DomainModel> {
    fun mapToDomainModel(responseModel: R?): D?
}