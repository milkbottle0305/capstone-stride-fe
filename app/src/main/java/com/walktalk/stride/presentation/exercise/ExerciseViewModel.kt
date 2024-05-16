package com.walktalk.stride.presentation.exercise

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng

class ExerciseViewModel : ViewModel() {
    private val _isPermissionGranted = mutableStateOf<Boolean?>(null)
    val isPermissionGranted = _isPermissionGranted.value
    private val _pathList = mutableStateListOf<LatLng>()
    val pathList = _pathList.toList()
    private val _speedList = mutableStateListOf<Float>()
    val speedList = _speedList.toList()
    private val _distance = mutableStateListOf<Float>()
    val distance = _distance.toList()
    private val _stride = mutableStateListOf<Float>()
    val stride = _stride.toList()
    private val _step = mutableStateListOf<Int>()
    val step = _step.toList()
    private val _duration = mutableStateOf<Long>(0)
    val duration = _duration.value

    fun setPermissionGranted(isGranted: Boolean) {
        _isPermissionGranted.value = isGranted
    }

    fun addPath(latLng: LatLng) {
        _pathList.add(latLng)
    }

    fun addSpeed(speed: Float) {
        _speedList.add(speed)
    }

    fun addDistance(distance: Float) {
        _distance.add(distance)
    }

    fun setTIme(time: Long) {
        _duration.value = time
    }

    fun addStride(stride: Float) {
        _stride.add(stride)
    }

    fun stopExercise() {
        // TODO: Implement stopExercise
    }
}