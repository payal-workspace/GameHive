package com.example.presentation.main.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PageIndicatorRow(images: List<Int>, pagerState: PagerState) {
    Row(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        images.forEachIndexed { index, _ ->
            PageIndicator(isActive = index == pagerState.currentPage)
        }
    }
}

@Composable
fun PageIndicator(isActive: Boolean) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(8.dp)
            .clip(CircleShape)
            .background(if (isActive) Color.Red else Color.Gray)
    )
}
