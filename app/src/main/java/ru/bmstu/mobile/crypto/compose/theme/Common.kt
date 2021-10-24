package ru.bmstu.mobile.crypto.compose.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

data class CryptoColors(
    val primaryText: Color,
    val primaryBackground: Color,
    val secondaryText: Color,
    val secondaryBackground: Color,
    val tintColor: Color,
    val controlColor: Color,
    val errorColor: Color,
)

data class CryptoShape(
    val padding: Dp,
    val cornerStyle: Shape
)

data class CryptoTypography(
    val heading: TextStyle,
    val body: TextStyle,
    val toolbar: TextStyle,
    val caption: TextStyle
)

object CryptoTheme {
    val colors: CryptoColors
        @Composable
        get() = LocalCryptoColors.current

    val shape: CryptoShape
        @Composable
        get() = LocalCryptoShape.current

    val typography: CryptoTypography
        @Composable
        get() = LocalCryptoTypography.current
}

enum class CryptoCorners {
    Flat, Rounded
}

enum class CryptoSize {
    Small, Medium, Big
}

enum class CryptoStyle {
    Red, Blue, Green, Purple, Orange
}

enum class CryptoAnimations {}

val LocalCryptoColors = staticCompositionLocalOf<CryptoColors> {
    error("No colors provided")
}

val LocalCryptoShape = staticCompositionLocalOf<CryptoShape> {
    error("No shape provided")
}

val LocalCryptoTypography = staticCompositionLocalOf<CryptoTypography> {
    error("No typography provided")
}
