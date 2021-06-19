package com.example.domain.repositories

import com.example.domain.Result
import com.example.domain.models.CovidStats
import kotlinx.coroutines.flow.Flow

interface CovidStatsRepository {

    suspend fun fetchStats(): Flow<Result<List<CovidStats>>>
}