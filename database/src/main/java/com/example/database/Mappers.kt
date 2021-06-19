package com.example.database

import com.example.database.db.CovidStatsEntity
import com.example.domain.models.CovidStats

internal fun CovidStats.toEntity() =
    CovidStatsEntity(null, this.date, this.hospitalizedCumulative, this.hospitalizedCurrently)

internal fun CovidStatsEntity.toDomain() =
    CovidStats(this.date, this.hospitalizedCumulative, this.hospitalizedCurrently)