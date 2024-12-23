package com.example.presentation.main.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.domain.model.SportsModelLists
import com.example.presentation.R

@Composable
fun SportsItem(sportsData: SportsModelLists) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.corner_radius)))
            .background(Color.White)
            .padding(vertical = dimensionResource(id = R.dimen.padding_10))
    ) {
        SportsItemRow(sportsItem = sportsData)
    }
}

@Composable
fun SportsItemRow(sportsItem: SportsModelLists) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
        ),
        modifier = Modifier.fillMaxWidth(),
        content = {
            Row(
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_8))
                    .fillMaxWidth()
            ) {
                SportsItemImage(data = sportsItem)
                SportsItemDetails(data = sportsItem)
            }
        }
    )

}

@Composable
fun SportsItemImage(data: SportsModelLists) {
    Image(
        modifier = Modifier
            .width(dimensionResource(id = R.dimen.padding_60))
            .aspectRatio(1f)
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.padding_10))),
        painter = painterResource(id = data.sportsImageUrl),
        contentDescription = "Sport Item Image",
        contentScale = ContentScale.FillBounds
    )
}

@Composable
fun SportsItemDetails(data: SportsModelLists) {
    Column(
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.medium_margin))
            .fillMaxHeight()
    ) {
        Text(
            text = data.sportsTitle,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = data.sportsTitle,
            style = MaterialTheme.typography.titleMedium
        )
    }
}
