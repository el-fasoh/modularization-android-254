package com.example.database

import com.example.database.db.CovidStatsEntity
import com.example.domain.models.CovidStats

fun CovidStats.toEntity() =
    CovidStatsEntity(null, this.date, this.hospitalizedCumulative, this.hospitalizedCurrently)