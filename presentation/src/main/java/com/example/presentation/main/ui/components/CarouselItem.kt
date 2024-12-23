package com.example.presentation.main.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.presentation.R

@Composable
fun CarouselItem(
    @DrawableRes resourceId: Int,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    shape: RoundedCornerShape = RoundedCornerShape(8.dp),
    padding: Dp = 8.dp
) {
    Box(
        modifier = modifier
            .padding(horizontal = padding)
            .clip(shape)
            .fillMaxWidth()
            .aspectRatio(1.5f)
    ) {
        Image(
            painter = painterResource(id = resourceId),
            contentDescription = "Carousel Image",
            contentScale = contentScale,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview
@Composable
fun PreviewCarouselItem() {
    CarouselItem(
        resourceId = R.drawable.bg_list_item,
        shape = RoundedCornerShape(12.dp),
        padding = 16.dp
    )
}
