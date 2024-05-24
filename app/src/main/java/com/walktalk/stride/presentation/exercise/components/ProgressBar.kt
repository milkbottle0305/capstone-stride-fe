package com.walktalk.stride.presentation.exercise.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.walktalk.stride.R
import com.walktalk.stride.ui.theme.StrideTheme

@Composable
fun ProgressBar(progress: Float) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.step),
            contentDescription = null,
            modifier = Modifier
                .height(40.dp)
                .width(40.dp),
            alignment = Alignment.CenterStart
        )
        Spacer(modifier = Modifier.width(17.dp))
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .width(280.dp)
                .height(20.dp)
                .clip(RoundedCornerShape(5.dp)),
            color = StrideTheme.colors.progress,
            trackColor = StrideTheme.colors.progressBackground,
        )
    }
}

@Preview
@Composable
fun ProgressBarPreview() {
    ProgressBar(0.5f)
}