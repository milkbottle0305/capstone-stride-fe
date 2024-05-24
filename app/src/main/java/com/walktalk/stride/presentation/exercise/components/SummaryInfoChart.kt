package com.walktalk.stride.presentation.exercise.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.core.chart.line.LineChart
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.FloatEntry
import com.walktalk.stride.ui.theme.StrideTheme

@Composable
fun SummaryInfoChart(data: List<Number>) {
    val chartEntryList =
        data.mapIndexed { index, value -> FloatEntry(index.toFloat(), value.toFloat()) }
    val producer = ChartEntryModelProducer(chartEntryList)
    val lineSpec = listOf(
        LineChart.LineSpec(
            lineColor = StrideTheme.colors.progress.toArgb()
        ),
    )
    Chart(
        chart = LineChart(
            lines = lineSpec,
        ),
        chartModelProducer = producer,
    )
}