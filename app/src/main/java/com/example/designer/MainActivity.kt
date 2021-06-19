package com.example.designer

import android.content.Context
import android.graphics.Color.green
import android.graphics.Color.red
import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.database.repo.CovidStatsRepository
import com.example.domain.Result
import com.example.network.service.CovidStatsServiceImpl
import com.google.android.material.textview.MaterialTextView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: CovidStatsRepository

    @Inject
    lateinit var service: CovidStatsServiceImpl

   lateinit var statusView: MaterialTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         statusView = findViewById(R.id.status)
        lifecycleScope.launch {
            repository.fetchStats().collect { result ->
                when(result) {
                    is Result.Error -> setResult(R.color.red, result.exception.message.orEmpty())
                    Result.Loading -> setResult(R.color.purple,"Loading")
                    is Result.Success -> setResult(R.color.green, "Loaded: ${result.data.size} items")
                }
            }
        }

    }

    private fun setResult(@ColorRes color: Int, message: String) {
        statusView.apply {
            text = message
            setTextColor(ContextCompat.getColor(this@MainActivity, color))
        }
    }


}