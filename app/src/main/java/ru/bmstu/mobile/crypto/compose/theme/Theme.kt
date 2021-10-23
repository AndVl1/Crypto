package ru.bmstu.mobile.crypto.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.dp

@Composable
fun MainTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    corners: CryptoCorners = CryptoCorners.Rounded,
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        baseDarkPalette
    } else {
        baseLightPalette
    }

    val shapes = CryptoShape(
        padding = 8.dp,
        cornerStyle = if (corners == CryptoCorners.Rounded) {
            RoundedCornerShape(8.dp)
        } else {
            RoundedCornerShape(2.dp)
        }
    )

    CompositionLocalProvider(
        LocalCryptoColors provides colors,
        LocalCryptoShape provides shapes,
        content = content
    )
}
