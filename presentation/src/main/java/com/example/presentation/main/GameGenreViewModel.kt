package com.example.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.common.utils.Resource
import com.example.domain.model.SportsModel
import com.example.domain.model.SportsModelData
import com.example.domain.model.SportsModelLists
import com.example.domain.usecase.GetSportsCategoriesUseCase
import com.example.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameGenreViewModel @Inject constructor(
    private val getSportsCategoriesUseCase: GetSportsCategoriesUseCase
) : BaseViewModel() {

    private val _sportsCategories = MutableStateFlow<Resource<SportsModel>>(Resource.Loading)
    val sportsCategories: StateFlow<Resource<SportsModel>> = _sportsCategories

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _filteredCategories = MutableStateFlow<List<SportsModelData>>(emptyList())
    val filteredCategories: StateFlow<List<SportsModelData>> = _filteredCategories

    private val _sportsCategoriesLists = MutableStateFlow<List<SportsModelLists>>(emptyList())
    val sportsCategoriesLists: StateFlow<List<SportsModelLists>> = _sportsCategoriesLists

    private val _showBottomSheet = MutableSharedFlow<Boolean>()
    val showBottomSheet: SharedFlow<Boolean> = _showBottomSheet

    private val _topCharacters = MutableStateFlow("No data available")
    val topCharacters: StateFlow<String> = _topCharacters

    init {
        fetchSportsCategories()
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        filterCategories()
    }

    private fun fetchSportsCategories() {
        launchWithState(
            block = { getSportsCategoriesUseCase().first() },
            onSuccess = { resource ->
                _sportsCategories.emit(resource)
                if (resource is Resource.Success) {
                    val categories = resource.data.data.orEmpty()
                    _filteredCategories.emit(categories)
                    val allSubCategories = categories.flatMap { it.items.orEmpty() }
                    _sportsCategoriesLists.emit(allSubCategories)
                }
            }
        )
    }

    private fun filterCategories() {
        val query = _searchQuery.value.lowercase()
        val categories = (sportsCategories.value as? Resource.Success)?.data?.data.orEmpty()

        _filteredCategories.value = categories.filter { category ->
            category.name.lowercase().contains(query) ||
                    category.items.orEmpty().any { item ->
                        item.game_title.lowercase().contains(query)
                    }
        }
    }

    fun showBottomSheet() = viewModelScope.launch {
        calculateTopCharacters()
        _showBottomSheet.emit(true)
    }

    private fun calculateTopCharacters() {
        val characterCount = _filteredCategories.value
            .flatMap { category ->
                category.items.orEmpty().flatMap { it.game_title.lowercase().toList() }
            }
            .groupingBy { it }
            .eachCount()
            .entries
            .sortedByDescending { it.value }
            .take(3)
            .joinToString("\n") { "${it.key} -> ${it.value}" }

        _topCharacters.value = characterCount.ifEmpty { "No data available" }
    }
}




