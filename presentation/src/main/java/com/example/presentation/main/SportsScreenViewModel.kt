package com.example.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.common.utils.Resource
import com.example.domain.model.SportsModel
import com.example.domain.model.SportsModelData
import com.example.domain.model.SportsModelLists
import com.example.domain.usecase.GetSportsCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportsScreenViewModel @Inject constructor(
    private val getSportsCategoriesUseCase: GetSportsCategoriesUseCase
) : ViewModel() {

    private val _sportsCategories = MutableStateFlow<Resource<SportsModel>>(Resource.Loading)
    val sportsCategories: StateFlow<Resource<SportsModel>> = _sportsCategories

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _filteredCategories = MutableStateFlow<List<SportsModelData>>(emptyList())
    val filteredCategories: StateFlow<List<SportsModelData>> = _filteredCategories

    private val _sportsCategoriesLists = MutableStateFlow<List<SportsModelLists>>(emptyList())
    val sportsCategoriesLists: StateFlow<List<SportsModelLists>> = _sportsCategoriesLists

    private val _topCharacters = MutableStateFlow("No data available")
    val topCharacters: StateFlow<String> get() = _topCharacters

    private val _showBottomSheet = MutableStateFlow(false)
    val showBottomSheet: StateFlow<Boolean> get() = _showBottomSheet

    init {
        fetchSportsCategories()
    }



    fun fetchSportsCategories() = viewModelScope.launch {
        try {
            val resource = getSportsCategoriesUseCase().first()
            _sportsCategories.emit(resource)

            if (resource is Resource.Success) {
                val categories = resource.data.data.orEmpty()
                _filteredCategories.emit(categories)
                updateCategoryItems(0)
            } else if (resource is Resource.Failure) {
                _sportsCategories.emit(Resource.Failure(resource.error))
            }
        } catch (exception: Exception) {
            _sportsCategories.emit(Resource.Failure(exception))
        }
    }

    fun updateCategoryItems(pageIndex: Int) {
        if (filteredCategories.value.isEmpty()) return
        val items = filteredCategories.value.getOrNull(pageIndex)?.sportsCategoryItem.orEmpty()
        _sportsCategoriesLists.value = items
        clearSearchQuery()

    }

    private fun clearSearchQuery(){
        _searchQuery.value = ""
    }


    fun onSearchQueryChanged(query: String, pageIndex: Int) {
        _searchQuery.value = query
        val items = filteredCategories.value.getOrNull(pageIndex)?.sportsCategoryItem.orEmpty()
        _sportsCategoriesLists.value = if (query.isBlank()) {
            items
        } else {
            items.filter { it.sportsTitle.contains(query, ignoreCase = true) }
        }
    }

    fun showBottomSheet() = viewModelScope.launch {
        calculateTopCharacters()
        _showBottomSheet.emit(true)
    }

    private fun calculateTopCharacters() {
        val currentPageIndex = filteredCategories.value.indexOfFirst {
            it.sportsCategoryItem == _sportsCategoriesLists.value
        }

        val itemsOnCurrentPage = filteredCategories.value.getOrNull(currentPageIndex)?.sportsCategoryItem.orEmpty()

        val characterCount = itemsOnCurrentPage
            .flatMap { it.sportsTitle.lowercase().toList() }
            .groupingBy { it }
            .eachCount()
            .entries
            .sortedByDescending { it.value }
            .take(3)
            .joinToString("\n") { "\u2022 " + "${ it.key} -> ${it.value}" }

        _topCharacters.value = characterCount.ifEmpty { "No data available" }
    }
}

