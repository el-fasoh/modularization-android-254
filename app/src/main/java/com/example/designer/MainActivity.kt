package com.example.designer

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.domain.Result
import com.example.domain.repositories.CovidStatsRepository
import com.google.android.material.textview.MaterialTextView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: CovidStatsRepository
    private lateinit var statusView: MaterialTextView
    private val viewmodel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        statusView = findViewById(R.id.status)
        viewmodel.stats.observe(this) { result ->
            when (result) {
                is Result.Error -> setResult(R.color.red, result.exception.message.orEmpty())
                Result.Loading -> setResult(R.color.purple, "Loading")
                is Result.Success -> setResult(
                    R.color.green,
                    "Loaded: ${result.data.size} items"
                )
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