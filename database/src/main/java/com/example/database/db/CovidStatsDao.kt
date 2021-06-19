package com.example.database.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
internal interface CovidStatsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entries: List<CovidStatsEntity>)

    @Query(value = "select * from stats")
    fun fetchAll(): Flow<List<CovidStatsEntity>>

    @Query(value = "select count(*) from stats")
    suspend fun count(): Int
}