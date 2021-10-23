package ru.bmstu.mobile.crypto.compose.custom

import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ClickableCard(
    modifier: Modifier,
    elevation: Dp = 2.dp,
    shape: Shape = RoundedCornerShape(2.dp),
    backgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = contentColorFor(backgroundColor),
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .focusable()
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = modifier,
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            elevation = elevation,
            shape = shape,
        ) {
            content.invoke()
        }
    }
}