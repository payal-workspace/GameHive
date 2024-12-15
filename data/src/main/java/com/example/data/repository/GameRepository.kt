package com.example.data.repository

import com.example.core.common.utils.Resource
import com.example.data.model.GamesResponse

interface GameRepository {
    suspend fun getDetail(): Resource<GamesResponse>
}