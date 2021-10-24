package ru.bmstu.mobile.crypto.chart.linechart.renderer

import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope

// https://github.com/tehras/charts
object NoLineShader : LineShader {
    override fun fillLine(drawScope: DrawScope, canvas: Canvas, fillPath: Path) {
        // Do nothing
    }
}
