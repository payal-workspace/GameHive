package com.example.presentation.main.ui.components

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
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.domain.model.SportsModelData
import com.example.domain.model.SportsModelLists
import com.example.presentation.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SportsContent(
    categories: List<SportsModelData>,
    filteredImages: List<Int>,
    filteredList: List<SportsModelLists>,
    pagerState: PagerState,
    searchQuery: String,
    padding: PaddingValues,
    onSearchTriggered: (String) -> Unit
) {
    Column(modifier = Modifier.padding(padding)) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_0)),
            contentPadding = PaddingValues(horizontal = dimensionResource(id = R.dimen.padding_16))
        ) {
            if (categories.isEmpty()) {
                item {
                    LoadingIndicator(modifier = Modifier.fillMaxWidth())
                }
            } else {
                item {
                    SportsCategoryPager(images = filteredImages, pagerState = pagerState)
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_12)))
                }
            }
            stickyHeader {
                SearchBar(
                    queryState = searchQuery,
                    onSearchTriggered = onSearchTriggered,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            itemsIndexed(filteredList) { _, category ->
                SportsItem(sportsData = category)
            }
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_16)))
    }
}