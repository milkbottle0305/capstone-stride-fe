package com.walktalk.stride.presentation.exercise.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.walktalk.stride.R
import com.walktalk.stride.presentation.exercise.ExerciseViewModel
import com.walktalk.stride.ui.theme.StrideTheme

@Composable
fun StopButton(modifier: Modifier, viewModel: ExerciseViewModel) {
    Surface(
        modifier = modifier
            .clip(shape = RoundedCornerShape(5.dp))
            .clickable { viewModel.stopExercise() },
        color = StrideTheme.colors.buttonPrimary
    ) {
        Text(
            modifier = Modifier.padding(vertical = 9.dp, horizontal = 10.dp),
            text = stringResource(id = R.string.exercise_stop),
            color = StrideTheme.colors.textTertiary,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
    }
}