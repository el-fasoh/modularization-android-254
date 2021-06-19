package com.example.domain.models

class StatsErrorHandler (override val message: String?, val error: String?): Throwable()