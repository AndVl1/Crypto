package ru.bmstu.mobile.crypto.compose.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp

data class CryptoColors(
    val primaryText: Color,
    val primaryBackground: Color,
    val secondaryText: Color,
    val secondaryBackground: Color,
    val tintColor: Color,
    val errorColor: Color,
)

data class CryptoShape(
    val padding: Dp,
    val cornerStyle: Shape
)

object CryptoTheme {
    val colors: CryptoColors
        @Composable
        get() = LocalCryptoColors.current

    val shape: CryptoShape
        @Composable
        get() = LocalCryptoShape.current
}

enum class CryptoCorners {
    Flat, Rounded
}

val LocalCryptoColors = staticCompositionLocalOf<CryptoColors> {
    error("No colors provided")
}

val LocalCryptoShape = staticCompositionLocalOf<CryptoShape> {
    error("No shape provided")
}
