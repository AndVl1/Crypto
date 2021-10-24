package ru.bmstu.mobile.crypto.chart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import ru.bmstu.mobile.crypto.chart.linechart.LineChart
import ru.bmstu.mobile.crypto.chart.linechart.LineChartData
import ru.bmstu.mobile.crypto.chart.linechart.point.FilledCircularPointDrawer
import ru.bmstu.mobile.crypto.chart.linechart.renderer.SolidLineDrawer
import ru.bmstu.mobile.crypto.chart.linechart.simpleChartAnimation
import ru.bmstu.mobile.crypto.chart.linechart.xaxis.SimpleXAxisDrawer
import ru.bmstu.mobile.crypto.chart.linechart.yaxis.SimpleYAxisDrawer
import ru.bmstu.mobile.crypto.compose.theme.CryptoTheme
import ru.bmstu.mobile.crypto.extensions.toDate
import ru.bmstu.mobile.crypto.main.ErrorScreen
import ru.bmstu.mobile.crypto.main.LoadingEvent
import ru.bmstu.mobile.crypto.network.ListScreenState

@Composable
fun ChartScreen(
    viewModel: ChartViewModel
) {
    val state = viewModel.cryptoHistory.collectAsState(ListScreenState.Loading)

    Surface(
        color = CryptoTheme.colors.primaryBackground,
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TopAppBar(backgroundColor = CryptoTheme.colors.primaryBackground) {
                Text(
                    text = "Chart",
                    style = CryptoTheme.typography.toolbar,
                    modifier = Modifier.padding(start = CryptoTheme.shape.padding)
                )
            }

            when(state.value) {
                is ListScreenState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = CryptoTheme.colors.tintColor)
                    }
                }
                is ListScreenState.Error -> {
                    ErrorScreen()
                }
                is ListScreenState.Loaded -> {
                    val data = (state.value as ListScreenState.Loaded).data
                    val verticalOffset = remember {
                        mutableStateOf(5f)
                    }
                    val horizontalOffset = remember {
                        mutableStateOf(5f)
                    }
                    Row {
                        LineChart(
                            lineChartData = LineChartData(
                                points = data.data.map {
                                    LineChartData.Point(it.high.toFloat(), it.time.toDate())
                                },
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(480.dp)
                                .padding(CryptoTheme.shape.padding)
                                .wrapContentHeight()
                                .weight(1f),
                            animation = simpleChartAnimation(),
                            pointDrawer = FilledCircularPointDrawer(),
                            lineDrawer = SolidLineDrawer(
                            ),
                            xAxisDrawer = SimpleXAxisDrawer(
                                labelRatio = 5,
                                labelTextColor = CryptoTheme.colors.primaryText,
                                axisLineColor = CryptoTheme.colors.primaryText
                            ),
                            yAxisDrawer = SimpleYAxisDrawer(
                                labelTextColor = CryptoTheme.colors.primaryText,
                                axisLineColor = CryptoTheme.colors.primaryText,
                            ),
                            horizontalOffset = verticalOffset.value
                        )
                    }
                    Slider(
                        value = verticalOffset.value,
                        onValueChange = { verticalOffset.value = it },
                        valueRange = 0f..25f,
                        colors = SliderDefaults.colors(
                            thumbColor = CryptoTheme.colors.tintColor,
                            activeTrackColor = CryptoTheme.colors.tintColor,
                            inactiveTrackColor = CryptoTheme.colors.tintColor.copy(alpha = .3f)
                        ),
                        modifier = Modifier.padding(bottom = CryptoTheme.shape.padding)
                    )
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                        .weight(1f))
                }
            }

        }
    }

    LaunchedEffect(key1 = state){
        viewModel.handleIntent(LoadingEvent.EnterScreen)
    }
}