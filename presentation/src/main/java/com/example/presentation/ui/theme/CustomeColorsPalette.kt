package com.example.presentation.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class CustomColorsPalette(
    val primary: Color = Color.Unspecified,
    val secondary: Color = Color.Unspecified,
    val tertiary: Color = Color.Unspecified,
    val custom1OnBackground: Color = Color.Unspecified,
    val custom2OnBackground: Color = Color.Unspecified,
    val custom1OnSurface: Color = Color.Unspecified,
    val custom2OnSurface: Color = Color.Unspecified,
    val other1: Color = Color.Unspecified,
    val other2: Color = Color.Unspecified,
    val windowBackground: Color = Color.Unspecified,
    val CardBg: Color = Color.Unspecified,
    val Black: Color = Color.Unspecified,
    val Cyan: Color = Color.Unspecified,
    val Gray: Color = Color.Unspecified,
    val Blue40: Color = Color.Unspecified
)

val LocalCustomColorsPalette = staticCompositionLocalOf { CustomColorsPalette() }

val OnLightCustomColorsPalette = CustomColorsPalette(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    windowBackground = windowBackground,
    CardBg = CardBg,
    Black = Black,
    Cyan = Cyan,
    Gray = Gray,
    Blue40 = Blue40,
    custom1OnBackground = Color(color = 0xFF1A237E),
    custom2OnBackground = Color(color = 0xFF1B5E20),
    custom1OnSurface = Color(color = 0xFFE53935),
    custom2OnSurface = Color(color = 0xFFD81B60),
    other1 = Color(color = 0xFF006064),
    other2 = Color(color = 0xFF643700)
)

val OnDarkCustomColorsPalette = CustomColorsPalette(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    windowBackground = windowBackground,
    CardBg = CardBg,
    Black = Black,
    Cyan = Cyan,
    Gray = Gray,
    Blue40 = Blue40,
    custom1OnBackground = Color(color = 0xFF1E88E5),
    custom2OnBackground = Color(color = 0xFF43A047),
    custom1OnSurface = Color(color = 0xFFC62828),
    custom2OnSurface = Color(color = 0xFFAD1457),
    other1 = Color(color = 0xFF00897B),
    other2 = Color(color = 0xFF896200)
)
