package com.example.presentation.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.common.utils.Resource
import com.example.presentation.R
import com.example.presentation.main.ui.components.ErrorView
import com.example.presentation.main.ui.components.LoadingIndicator
import com.example.presentation.main.ui.components.SearchBar
import com.example.presentation.main.ui.components.SportsCategoryPager
import com.example.presentation.main.ui.components.SportsItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameMainScreen(viewModel: GameGenreViewModel = hiltViewModel()) {

    val categories by viewModel.filteredCategories.collectAsState()
    val sublistItems by viewModel.sportsCategoriesLists.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    val pageIndex by remember { mutableIntStateOf(0) }

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { categories.size }
    )
    LaunchedEffect(searchQuery, pageIndex) {
        viewModel.onSearchQueryChanged(searchQuery, pageIndex)
    }
    LaunchedEffect(searchQuery, pageIndex) {
        viewModel.onSearchQueryChanged(searchQuery, pageIndex)
    }

    LaunchedEffect(pagerState.currentPage) {
        viewModel.updateCategoryItems(pagerState.currentPage)
    }
    val filteredImages = categories.map { it.sportsCategoryImageUrl }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { padding ->
        val sportsCategories by viewModel.sportsCategories.collectAsState()

        when (sportsCategories) {
            is Resource.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    LoadingIndicator()
                }
            }
            is Resource.Failure -> {
                val errorMessage = (sportsCategories as Resource.Failure).error ?: "Something went wrong"
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    ErrorView(
                        message = errorMessage.toString(),
                        onRetry = { viewModel.fetchSportsCategories() }
                    )
                }
            }
            is Resource.Success -> {
                Column(modifier = Modifier.padding(padding)) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                        verticalArrangement = Arrangement.spacedBy((dimensionResource(id = R.dimen.padding_0))),
                        contentPadding = PaddingValues(horizontal = (dimensionResource(id = R.dimen.padding_16)))
                    ) {
                        if (filteredImages.isEmpty()) {
                            item {
                                LoadingIndicator(
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        } else {
                            item {
                                SportsCategoryPager(
                                    images = filteredImages,
                                    pagerState = pagerState
                                )
                                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_12)))
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
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_16)))
                }
            }
        }
    }

}
