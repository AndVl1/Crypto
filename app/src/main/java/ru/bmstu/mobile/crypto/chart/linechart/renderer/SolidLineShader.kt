package ru.bmstu.mobile.crypto.chart.linechart.renderer

import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope

// https://github.com/tehras/charts
class SolidLineShader(val color: Color = Color.Blue) : LineShader {
    override fun fillLine(drawScope: DrawScope, canvas: Canvas, fillPath: Path) {
        drawScope.drawPath(
            path = fillPath,
            color = color
        )
    }
}
