package com.example.gamehive

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.core.common.utils.Resource
import com.example.presentation.viewModel.viewModel.GameGenreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: GameGenreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launchWhenStarted {
            viewModel.categories.collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        resource.data
                        resource.data.items?.forEach {
                            Log.d("Data Source ", it.game_title)
                        }
                    }
                    is Resource.Failure -> {
                    }
                }
            }
        }
        viewModel.fetchGameCategories()
    }
}