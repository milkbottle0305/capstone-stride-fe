package com.walktalk.stride.presentation.exercise

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState

class ExerciseViewModel : ViewModel() {
    val process = 0.5f

    private val _pathList = mutableStateOf(listOf<LatLng>())
    val pathList = _pathList
    private val _cameraPositionState = mutableStateOf(CameraPositionState())
    val cameraPositionState: State<CameraPositionState> = _cameraPositionState

    fun setCameraPositionState(cameraPositionState: CameraPositionState) {
        _cameraPositionState.value = cameraPositionState
    }

    fun addPath(latLng: LatLng) {
        _pathList.value = _pathList.value + latLng
    }
}