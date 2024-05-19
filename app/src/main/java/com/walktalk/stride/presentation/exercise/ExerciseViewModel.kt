package com.walktalk.stride.presentation.exercise

import android.location.Location
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState

class ExerciseViewModel : ViewModel() {
    val process = 0.5f
    private val delayMillis: Long = 5000

    private val _pathList = mutableStateOf(listOf<LatLng>())
    val pathList: State<List<LatLng>> = _pathList
    private val _distanceList = mutableStateOf(listOf<Double>())
    val distanceList: State<List<Double>> = _distanceList
    private val _stepList = mutableStateOf(listOf<Int>())
    val stepList: State<List<Int>> = _stepList
    private val _strideList = mutableStateOf(listOf<Double>())
    val strideList: State<List<Double>> = _strideList
    private val _speedList = mutableStateOf(listOf<Double>())
    val speedList: State<List<Double>> = _speedList
    private val _cameraPositionState = mutableStateOf(CameraPositionState())
    val cameraPositionState: State<CameraPositionState> = _cameraPositionState

    fun setCameraPositionState(cameraPositionState: CameraPositionState) {
        _cameraPositionState.value = cameraPositionState
    }

    fun addPath(latLng: LatLng) {
        _pathList.value = _pathList.value + latLng
        if (_pathList.value.isNotEmpty()) {
            val distanceFloat = FloatArray(1)
            Location.distanceBetween(
                _pathList.value.last().latitude,
                _pathList.value.last().longitude,
                latLng.latitude,
                latLng.longitude,
                distanceFloat
            )
            val distance = distanceFloat[0].toDouble()
            Log.d("ExerciseViewModel", "addPath: $distance")
            _distanceList.value = _distanceList.value + distance
            _speedList.value = _speedList.value + ((distance / delayMillis) * 1000)
            if (_stepList.value.last() != 0) {
                _strideList.value = _strideList.value + ((distance * 100) / stepList.value.last())
            } else {
                _strideList.value = _strideList.value + 0.0
            }

        }}

    fun addStep(step: Int) {
        _stepList.value = _stepList.value + step
    }
}