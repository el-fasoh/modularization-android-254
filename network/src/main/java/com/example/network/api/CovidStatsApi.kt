package com.example.network.api

import com.example.domain.models.CovidStats
import retrofit2.Response
import retrofit2.http.GET

internal interface CovidStatsApi {

    @GET(value = "daily.json")
    suspend fun getStats(): Response<List<CovidStats>>
}