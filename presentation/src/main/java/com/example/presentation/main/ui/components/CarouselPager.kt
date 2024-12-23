package com.example.presentation.main.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageCarousel(images: List<Int>, pagerState: PagerState) {
    Column(modifier = Modifier.fillMaxWidth()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) { pageIndex ->
            CarouselItem(imageResId = images[pageIndex])
        }
        PageIndicatorRow(images = images, pagerState = pagerState)
    }
}

@Composable
fun CarouselItem(@DrawableRes imageResId: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Gray)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = imageResId),
            contentDescription = "Carousel Image",
            contentScale = ContentScale.Crop
        )
    }
}

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

