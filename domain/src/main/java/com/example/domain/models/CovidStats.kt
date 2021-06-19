package com.example.domain.models

data class CovidStats(
    val date: Int,
    val hospitalizedCumulative: Int,
    val hospitalizedCurrently: Int,
) {
    override fun toString(): String {
        return "CovidStats(date=$date, hospitalizedCumulative=$hospitalizedCumulative, hospitalizedCurrently=$hospitalizedCurrently)"
    }
}