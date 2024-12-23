package com.example.presentation.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.main.ui.components.ImageCarousel
import com.example.presentation.main.ui.components.LoadingIndicator
import com.example.presentation.main.ui.components.SearchBar
import com.example.presentation.main.ui.components.SportsItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameMainScreen() {
    val viewModel: GameGenreViewModel = hiltViewModel()
    val topCharacters by viewModel.topCharacters.collectAsState()

    val categories by viewModel.filteredCategories.collectAsState()
    val sublistItems by viewModel.sportsCategoriesLists.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    val showBottomSheet by viewModel.showBottomSheet.collectAsState(initial = false)
    var pageIndex by remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(searchQuery, pageIndex) {
        viewModel.onSearchQueryChanged(searchQuery, pageIndex)
    }
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { categories.size }
    )
    LaunchedEffect(pagerState.currentPage) {
        viewModel.updateCategoryItems(pagerState.currentPage)
    }
    val filteredImages = categories.map { it.sportsCategoryImageUrl }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.spacedBy(0.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                if (filteredImages.isEmpty()) {
                    item {
                        LoadingIndicator(
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                } else {
                    item {
                        ImageCarousel(
                            images = filteredImages,
                            pagerState = pagerState
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }

                stickyHeader {
                    SearchBar(
                        queryState = searchQuery,
                        onSearchTriggered = { newQuery ->
                            viewModel.onSearchQueryChanged(newQuery, pagerState.currentPage)
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                itemsIndexed(sublistItems) { _, category ->
                    SportsItem(sportsData = category)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

        }
    }
}
