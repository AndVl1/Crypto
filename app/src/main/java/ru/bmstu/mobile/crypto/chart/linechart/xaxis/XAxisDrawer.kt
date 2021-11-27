package ru.bmstu.mobile.crypto.chart.linechart.xaxis

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.drawscope.DrawScope

// https://github.com/tehras/charts
interface XAxisDrawer {
    fun requiredHeight(drawScope: DrawScope): Float

    fun drawAxisLine(
        drawScope: DrawScope,
        canvas: Canvas,
        drawableArea: Rect
    )

    fun drawAxisLabels(
        drawScope: DrawScope,
        canvas: Canvas,
        drawableArea: Rect,
        labels: List<String>
    )
}
