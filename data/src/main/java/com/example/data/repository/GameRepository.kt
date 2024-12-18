package com.example.data.repository

import com.example.core.common.utils.Resource
import com.example.data.model.SportsCategoryEntity
import com.example.domain.model.SportsModel

interface GameRepository {
    suspend fun getDetail(): Resource<SportsCategoryEntity>
}