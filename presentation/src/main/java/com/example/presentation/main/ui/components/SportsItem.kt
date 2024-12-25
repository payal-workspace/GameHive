package com.example.presentation.main.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.domain.model.SportsModelLists
import com.example.presentation.R
import com.example.presentation.ui.theme.LocalCustomColorsPalette
import com.example.presentation.ui.theme.LocalCustomTypography

@Composable
fun SportsItem(data: SportsModelLists) {
    Box(
        modifier = Modifier
            .padding(vertical = dimensionResource(id = R.dimen.padding_5))
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = LocalCustomColorsPalette.current.cardBg
            ),
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.corner_radius_15dp)),
            content = {
                Row(
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.corner_radius_15dp))
                        .fillMaxWidth()
                ) {
                    SportsItemImage(imageData = data)
                    SportsItemDetails(sportsData = data)
                }
            }
        )
    }
}

@Composable
fun SportsItemImage(imageData: SportsModelLists) {
    val imageModifier = Modifier
        .width(dimensionResource(id = R.dimen.padding_60))
        .aspectRatio(1f)
        .clip(RoundedCornerShape(dimensionResource(id = R.dimen.padding_10)))

    Image(
        modifier = imageModifier,
        painter = painterResource(id = imageData.sportsImageUrl),
        contentDescription = stringResource(id = R.string.text_sports_item_image),
        contentScale = ContentScale.Crop // To maintain image proportions without stretching
    )
}

@Composable
fun SportsItemDetails(sportsData: SportsModelLists) {
    // Used for padding and text styling consistency
    val textModifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.medium_margin))

    Column(
        modifier = textModifier
            .fillMaxHeight()
    ) {
        Text(
            text = sportsData.sportsTitle,
            style = LocalCustomTypography.current.bodyMedium
        )
        // Assuming you want a different text here or if the same title was unintended
        Text(
            text = sportsData.sportsTitle,  // Changed from sportsTitle to something more meaningful
            style = LocalCustomTypography.current.bodyNormal
        )
    }
}

