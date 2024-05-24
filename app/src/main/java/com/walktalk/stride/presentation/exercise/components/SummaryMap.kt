package com.walktalk.stride.presentation.exercise.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Polyline
import com.walktalk.stride.presentation.exercise.ExerciseViewModel
import com.walktalk.stride.ui.theme.StrideTheme

@Composable
fun SummaryMap(viewModel: ExerciseViewModel) {

    val pathList = viewModel.pathList.value
    val maxLat = pathList.maxByOrNull { it.latitude }?.latitude ?: 0.0
    val minLat = pathList.minByOrNull { it.latitude }?.latitude ?: 0.0
    val maxLng = pathList.maxByOrNull { it.longitude }?.longitude ?: 0.0
    val minLng = pathList.minByOrNull { it.longitude }?.longitude ?: 0.0
    val centerLat = (maxLat + minLat) / 2
    val centerLng = (maxLng + minLng) / 2
    val cameraPositionState = CameraPositionState(
        CameraPosition.fromLatLngZoom(
            LatLng(centerLat, centerLng),
            15f
        )
    )
    GoogleMap(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        cameraPositionState = cameraPositionState,
        uiSettings = MapUiSettings(
            zoomControlsEnabled = false,
            myLocationButtonEnabled = false,
            compassEnabled = false,
            rotationGesturesEnabled = false,
            scrollGesturesEnabled = false,
            tiltGesturesEnabled = false,
            zoomGesturesEnabled = false
        )
    ) {
        Polyline(
            points = pathList,
            color = StrideTheme.colors.error,
            width = 5f
        )
    }
}