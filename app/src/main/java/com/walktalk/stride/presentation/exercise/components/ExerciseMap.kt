package com.walktalk.stride.presentation.exercise.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Polyline
import com.walktalk.stride.presentation.exercise.ExerciseViewModel
import com.walktalk.stride.ui.theme.StrideTheme

@Composable
fun ExerciseMap(modifier: Modifier, viewModel: ExerciseViewModel) {
    val cameraPositionState = viewModel.cameraPositionState.value
    val pathList = viewModel.pathList.value
    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState,
        properties = MapProperties(isMyLocationEnabled = true),
    ) {
        Polyline(
            points = pathList,
            color = StrideTheme.colors.error,
            width = 5f
        )
    }
}