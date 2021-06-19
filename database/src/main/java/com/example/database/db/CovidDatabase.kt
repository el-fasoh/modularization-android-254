package com.example.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CovidStatsEntity::class], version = 1)
abstract class CovidDatabase : RoomDatabase() {

    abstract fun covidStatsDao(): CovidStatsDao

    companion object {
        var database: CovidDatabase? = null

        fun getInstance(context: Context): CovidDatabase {
            database?.let {
                return it
            }

            return Room.databaseBuilder(context, CovidDatabase::class.java, "covid-db")
                .build()
        }
    }
}