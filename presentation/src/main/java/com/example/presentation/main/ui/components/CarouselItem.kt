package com.example.presentation.main.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.presentation.R

@Composable
fun SportsCategoryPagerItem(@DrawableRes imageResId: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.padding_200))
            .padding(horizontal = dimensionResource(id = R.dimen.padding_16))
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.padding_16)))
            .background(Color.Gray)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = imageResId),
            contentDescription = stringResource(id = R.string.text_carousel_image),
            contentScale = ContentScale.Crop
        )
    }
}
