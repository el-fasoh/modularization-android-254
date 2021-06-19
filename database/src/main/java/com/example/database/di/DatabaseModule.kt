package com.example.database.di

import android.content.Context
import com.example.database.db.CovidDatabase
import com.example.database.db.CovidStatsDao
import com.example.database.db.CovidStatsEntity
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
    fun provideCovidDatabase(context: Context): CovidDatabase = CovidDatabase.getInstance(context)

    @Provides
    @Singleton
    fun provideCovidStatsDao(database: CovidDatabase): CovidStatsDao = database.covidStatsDao()

    @Provides
    @Singleton
    fun provideEntity() = CovidStatsEntity(54564,46546,5644,4685468)
}