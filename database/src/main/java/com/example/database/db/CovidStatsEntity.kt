package com.example.database.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stats")
internal data class CovidStatsEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,

    val date: Int,

    @ColumnInfo(name = "hospitalized_cumulative")
    val hospitalizedCumulative: Int,

    @ColumnInfo(name = "hospitalized_currently")
    val hospitalizedCurrently: Int,
)