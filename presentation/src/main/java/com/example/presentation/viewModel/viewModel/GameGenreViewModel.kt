package com.example.presentation.viewModel.viewModel

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
class GameGenreViewModel @Inject constructor(
    private val getGamesCategoriesUseCase: GetGameCategoriesUseCase
) : ViewModel() {

    private val _categories = MutableStateFlow<Resource<GamesModel>>(Resource.Loading)
    val categories: StateFlow<Resource<GamesModel>> get() = _categories

    fun fetchGameCategories() {
        viewModelScope.launch {
            getGamesCategoriesUseCase().collect { resource ->

                when (resource) {
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        _categories.value = resource
                    }
                    is Resource.Failure -> {
                    }
                }
            }
        }
    }
}