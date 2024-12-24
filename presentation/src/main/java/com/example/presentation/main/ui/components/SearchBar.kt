package com.example.presentation.main.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.ui.theme.LocalCustomColorsPalette

@Composable
fun SearchBar(
    queryState: String,
    onSearchTriggered: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = queryState,
            textStyle = MaterialTheme.typography.bodySmall,
            onValueChange = { newText ->
                onSearchTriggered(newText)
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(1.5.dp,Color.Gray)
                .background(LocalCustomColorsPalette.current.windowBackground)
                .height(dimensionResource(id = R.dimen.padding_60)),
            placeholder = {
                Text(
                    text = "Search",
                    style = TextStyle(
                        fontSize = dimensionResource(id = R.dimen.search_text_size).value.sp,
                        fontWeight = FontWeight.Normal,
                        color = LocalCustomColorsPalette.current.Black
                    ),
                    maxLines = 1,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.text_search_icon),
                    tint = Color.Gray
                )
            },
            trailingIcon = {
                if (queryState.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = stringResource(id = R.string.text_clear_input),
                        modifier = Modifier.clickable {
                            onSearchTriggered("")
                          //  keyboardController?.hide()
                        },
                        tint = Color.Gray
                    )

                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    onSearchTriggered(queryState.lowercase().trim())
                }
            ),
            maxLines = 1,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.White
            )
        )
    }
}

@Preview
@Composable
fun PreviewSearchBar() {
    SearchBar(
        queryState = "sports",
        onSearchTriggered = {}
    )
}
