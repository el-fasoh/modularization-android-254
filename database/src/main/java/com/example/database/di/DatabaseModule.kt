package com.example.database.di

import android.content.Context
import com.example.database.db.CovidDatabase
import com.example.database.db.CovidStatsDao
import com.example.database.db.CovidStatsEntity
import com.example.database.repo.CovidStatsRepositoryImpl
import com.example.domain.repositories.CovidStatsRepository
import com.example.domain.services.CovidStatsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    internal fun provideCovidDatabase(context: Context): CovidDatabase =
        CovidDatabase.getInstance(context)

    @Provides
    @Singleton
    internal fun provideCovidStatsDao(database: CovidDatabase): CovidStatsDao =
        database.covidStatsDao()

    @Provides
    @Singleton
    internal fun provideCovidStatsRepository(
        covidStatsDao: CovidStatsDao,
        covidStatsService: CovidStatsService
    ): CovidStatsRepository = CovidStatsRepositoryImpl(covidStatsDao, covidStatsService)
}