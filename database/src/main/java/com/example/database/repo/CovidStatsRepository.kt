package com.example.database.repo

import com.example.database.db.CovidStatsDao
import com.example.database.db.CovidStatsEntity
import com.example.database.toEntity
import com.example.domain.services.CovidStatsService
import com.example.domain.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CovidStatsRepository @Inject constructor(
    private val covidStatsDao: CovidStatsDao,
    private val covidStatsService: CovidStatsService
) {

    fun fetchStats(): Flow<Result<List<CovidStatsEntity>>> = flow {
        emit(Result.Loading)
        if (covidStatsDao.count() == 0) {
            when (val result = covidStatsService.fetchCovidStats()) {
                is Result.Error -> emit(Result.Error(result.exception))
                is Result.Success -> {
                    val entities = result.data.map { it.toEntity() }
                    covidStatsDao.save(entities)
                    covidStatsDao.fetchAll().collect {
                        emit(Result.Success(it))
                    }
                }
            }
        } else {
            covidStatsDao.fetchAll().collect {
                emit(Result.Success(it))
            }
        }
    }

}