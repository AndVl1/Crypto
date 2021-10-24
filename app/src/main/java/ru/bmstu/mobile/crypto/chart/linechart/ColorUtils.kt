package ru.bmstu.mobile.crypto.chart.linechart

import androidx.compose.ui.graphics.Color

// https://github.com/tehras/charts
fun Color.toLegacyInt(): Int {
  return android.graphics.Color.argb(
    (alpha * 255.0f + 0.5f).toInt(),
    (red * 255.0f + 0.5f).toInt(),
    (green * 255.0f + 0.5f).toInt(),
    (blue * 255.0f + 0.5f).toInt()
  )
}