package ru.bmstu.mobile.crypto.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    corners: CryptoCorners = CryptoCorners.Rounded,
    textSize: CryptoSize = CryptoSize.Medium,
    paddingSize: CryptoSize = CryptoSize.Medium,
    style: CryptoStyle = CryptoStyle.Green,
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        when (style) {
            CryptoStyle.Green -> greenDarkPalette
            CryptoStyle.Red -> redDarkPalette
            CryptoStyle.Purple -> purpleDarkPalette
            CryptoStyle.Orange -> orangeDarkPalette
            CryptoStyle.Blue -> blueDarkPalette
        }
    } else {
        when (style) {
            CryptoStyle.Green -> greenLightPalette
            CryptoStyle.Red -> redLightPalette
            CryptoStyle.Purple -> purpleLightPalette
            CryptoStyle.Orange -> orangeLightPalette
            CryptoStyle.Blue -> blueLightPalette
        }
    }

    val shapes = CryptoShape(
        padding = when (paddingSize) {
            CryptoSize.Small -> 12.dp
            CryptoSize.Medium -> 16.dp
            CryptoSize.Big -> 20.dp
        },
        cornerStyle = if (corners == CryptoCorners.Rounded) {
            RoundedCornerShape(8.dp)
        } else {
            RoundedCornerShape(2.dp)
        }
    )

    val typography = CryptoTypography(
        heading = TextStyle(
            fontSize = when (textSize) {
                CryptoSize.Small -> 24.sp
                CryptoSize.Medium -> 28.sp
                CryptoSize.Big -> 32.sp
            },
            fontWeight = FontWeight.Bold,
            color = colors.primaryText,
        ),
        body = TextStyle(
            fontSize = when (textSize) {
                CryptoSize.Small -> 14.sp
                CryptoSize.Medium -> 16.sp
                CryptoSize.Big -> 18.sp
            },
            fontWeight = FontWeight.Normal,
            color = colors.primaryText,
        ),
        toolbar = TextStyle(
            fontSize = when (textSize) {
                CryptoSize.Small -> 14.sp
                CryptoSize.Medium -> 16.sp
                CryptoSize.Big -> 18.sp
            },
            fontWeight = FontWeight.Medium,
            color = colors.primaryText,
        ),
        caption = TextStyle(
            fontSize = when (textSize) {
                CryptoSize.Small -> 10.sp
                CryptoSize.Medium -> 12.sp
                CryptoSize.Big -> 14.sp
            },
            color = colors.primaryText,
        )
    )

    CompositionLocalProvider(
        LocalCryptoColors provides colors,
        LocalCryptoShape provides shapes,
        LocalCryptoTypography provides typography,
        content = content
    )
}
