package com.example.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.common.utils.Resource
import com.example.domain.model.GamesModel
import com.example.domain.usecase.GetGameCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesCatViewModel @Inject constructor(
    private val getGamesCategoriesUseCase: GetGameCategoriesUseCase
) : ViewModel() {

    private val _categories = MutableStateFlow<Resource<GamesModel>>(Resource.Loading)
    val categories: StateFlow<Resource<GamesModel>> get() = _categories

    fun fetchGameCategories() {
        // Launch the fetch in the viewModelScope
        viewModelScope.launch {
            // Collect the flow from the use case and emit it to the _categories StateFlow
            getGamesCategoriesUseCase().collect { resource ->

                when (resource) {
                    is Resource.Loading -> {
                        // Show loading spinner
                    }
                    is Resource.Success -> {
                        // Display the fetched data
                        _categories.value = resource
                    }
                    is Resource.Failure -> {
                        // Show error message
                    }
                }
            }
        }
    }
}