package com.walktalk.stride.presentation.exercise.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.walktalk.stride.R
import com.walktalk.stride.presentation.exercise.ExerciseViewModel
import com.walktalk.stride.ui.theme.StrideTheme

@Composable
fun StopButton(modifier: Modifier, viewModel: ExerciseViewModel) {
    val context = LocalContext.current
    Surface(
        modifier = modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .clickable {

            },
        color = StrideTheme.colors.buttonPrimary
    ) {
        Text(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 19.dp),
            text = stringResource(id = R.string.exercise_stop),
            color = StrideTheme.colors.textTertiary,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun StopButtonPreview() {
    StrideTheme {
        StopButton(Modifier, ExerciseViewModel())
    }
}