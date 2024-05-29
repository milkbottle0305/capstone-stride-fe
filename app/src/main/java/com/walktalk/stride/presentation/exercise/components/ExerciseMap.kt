package com.walktalk.stride.presentation.exercise.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Polyline
import com.walktalk.stride.ui.theme.StrideTheme

@Composable
fun ExerciseMap(
    modifier: Modifier,
    cameraPositionState: CameraPositionState,
    pathList: List<LatLng>
) {
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