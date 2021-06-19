package com.example.domain.services

import com.example.domain.Result
import com.example.domain.models.CovidStats

interface CovidStatsService {
    suspend fun fetchCovidStats(): Result<List<CovidStats>>
}