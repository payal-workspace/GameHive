package com.example.presentation.main.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.ui.theme.LocalCustomColorsPalette
import com.example.presentation.ui.theme.LocalCustomTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopCharactersBottomSheet(topCharacters: String, onClose: () -> Unit,
                             sheetState: SheetState,categoriesCount: Int) {
    ModalBottomSheet(
        modifier = Modifier.fillMaxSize(),
        onDismissRequest = onClose,
        sheetState = sheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        containerColor = LocalCustomColorsPalette.current.windowBackground
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(start = dimensionResource(id = R.dimen.padding_18)),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(id = R.string.number_of_sports) + "-> " +categoriesCount,
                style = TextStyle(
                    fontSize = dimensionResource(id = R.dimen.text_bottomsheet_views_size).value.sp,
                    fontWeight = FontWeight.Medium,
                    color = LocalCustomColorsPalette.current.Black
                ),
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_8))
            )
            Text(
                text = stringResource(id = R.string.top_characters),
                style = LocalCustomTypography.current.titleMedium.copy(
                    color = LocalCustomColorsPalette.current.Black),
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_8))
            )
            Text(
                text = topCharacters,
                style = LocalCustomTypography.current.titleMedium.copy(
                    fontSize = dimensionResource(id = R.dimen.text_bottomsheet_statistics_size).value.sp,
                    color = LocalCustomColorsPalette.current.Black),
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_8))
            )
        }
    }

}

