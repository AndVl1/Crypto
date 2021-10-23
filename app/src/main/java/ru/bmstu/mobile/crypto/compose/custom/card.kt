package ru.bmstu.mobile.crypto.compose.custom

import androidx.compose.foundation.clickable
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.bmstu.mobile.crypto.compose.theme.CryptoTheme

@Composable
fun ClickableCard(
    modifier: Modifier,
    elevation: Dp = 2.dp,
    shape: Shape = CryptoTheme.shape.cornerStyle,
    backgroundColor: Color = CryptoTheme.colors.secondaryBackground,
    contentColor: Color = CryptoTheme.colors.primaryText,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Card(
        modifier = modifier.clickable { onClick() },
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        elevation = elevation,
        shape = shape,
    ) {
        content.invoke()
    }
}