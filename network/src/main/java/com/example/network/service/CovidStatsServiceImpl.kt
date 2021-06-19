package com.example.network.service

import com.example.domain.models.CovidStats
import com.example.domain.services.CovidStatsService
import com.example.domain.Result
import com.example.network.RetrofitUtil.safeApiCall
import com.example.network.api.CovidStatsApi
import javax.inject.Inject

class CovidStatsServiceImpl @Inject constructor(private val covidStatsApi: CovidStatsApi) :
    CovidStatsService {

    override suspend fun fetchCovidStats(): Result<List<CovidStats>> = safeApiCall {
        covidStatsApi.getStats()
    }

}