package com.example.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameGenreViewModel @Inject constructor(
    private val getSportsCategoriesUseCase: GetSportsCategoriesUseCase
) : ViewModel() {

    // StateFlow for UI states
    private val _sportsCategoriesLists = MutableStateFlow<List<SportsModelLists>>(emptyList())
    val sportsCategoriesLists: StateFlow<List<SportsModelLists>> = _sportsCategoriesLists

    private val _filteredCategories = MutableStateFlow<List<SportsModelData>>(emptyList())
    val filteredCategories: StateFlow<List<SportsModelData>> = _filteredCategories

    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> = _loading

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private var allCategories: List<SportsModelData> = emptyList()

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            _loading.value = true
            try {
                // Simulate fetching data
                val categories = getSportsCategoriesUseCase()
                allCategories = categories
                _filteredCategories.value = categories
                _sportsCategoriesLists.value = categories.firstOrNull()?.sportsCategoryList ?: emptyList()
            } catch (e: Exception) {
                // Handle the error (e.g., log or show error state)
            } finally {
                _loading.value = false
            }
        }
    }

    fun onSearchQueryChanged(query: String, currentPageIndex: Int) {
        _searchQuery.value = query
        val filtered = if (query.isBlank()) {
            allCategories
        } else {
            allCategories.filter { category ->
                category.sportsCategoryList.any {
                    it.game_title.contains(query, ignoreCase = true)
                }
            }
        }
        _filteredCategories.value = filtered
        updateCategoryItems(currentPageIndex)
    }

    fun updateCategoryItems(currentPageIndex: Int) {
        val selectedCategory = _filteredCategories.value.getOrNull(currentPageIndex)
        _sportsCategoriesLists.value = selectedCategory?.sportsCategoryList ?: emptyList()
    }

    fun showBottomSheet() {
        // Logic to show a bottom sheet
    }
}

