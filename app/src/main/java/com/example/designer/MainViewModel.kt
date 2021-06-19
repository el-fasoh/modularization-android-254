package com.example.designer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.domain.repositories.CovidStatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(covidStatsRepository: CovidStatsRepository): ViewModel() {

    val stats = covidStatsRepository.fetchStats().asLiveData()
}