package ru.bmstu.mobile.crypto.chart.linechart.point

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.drawscope.DrawScope

// https://github.com/tehras/charts
object NoPointDrawer : PointDrawer {
  override fun drawPoint(
    drawScope: DrawScope,
    canvas: Canvas,
    center: Offset
  ) {
    // Leave empty on purpose, we do not want to draw anything.
  }
}