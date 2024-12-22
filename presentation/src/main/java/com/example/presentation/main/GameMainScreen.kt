package com.example.presentation.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun GameMainScreen(
    viewModel: GameGenreViewModel = hiltViewModel(),
    onFabClick: () -> Unit,
    onSearchQueryChange: (String) -> Unit
) {
    val sportsCategoriesLists by viewModel.sportsCategoriesLists.collectAsState()
    val filteredCategories by viewModel.filteredCategories.collectAsState()
    val isLoading by viewModel.loading.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onFabClick) {
                Icon(imageVector = Icons.Default.Email, contentDescription = "Show Bottom Sheet")
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(padding)
        ) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                Column {
                    AppBar(
                        searchQuery = searchQuery,
                        onQueryChanged = onSearchQueryChange
                    )
                    CarouselView(
                        categories = filteredCategories,
                        onPageChange = { pageIndex -> viewModel.updateCategoryItems(pageIndex) }
                    )
                    SportsCategoryList(sportsCategoriesLists)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(searchQuery: String, onQueryChanged: (String) -> Unit) {
    TopAppBar(
        title = {
            SearchBar(
                query = searchQuery,
                onQueryChanged = onQueryChanged
            )
        }
    )
}

@Composable
fun SearchBar(query: String, onQueryChanged: (String) -> Unit) {
    TextField(
        value = query,
        onValueChange = onQueryChanged,
        placeholder = { Text(text = "Search") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarouselView(categories: List<SportsModelData>, onPageChange: (Int) -> Unit) {
    val pagerState = rememberPagerState()

    Column {
        HorizontalPager(
            state = pagerState,
            pageSize = categories.size,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) { page ->
            val category = categories[page]
            Image(
                painter = painterResource(category.gamePosterImage),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }

        // Tab Indicator
        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }

    LaunchedEffect(pagerState.currentPage) {
        onPageChange(pagerState.currentPage)
    }
}

@Composable
fun SportsCategoryList(items: List<SportsModelLists>) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items) { item ->
            SportsCategoryItem(item)
        }
    }
}

@Composable
fun SportsCategoryItem(item: SportsModelLists) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(item.img_sports_type_item),
                contentDescription = item.game_title,
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = item.game_title,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}
