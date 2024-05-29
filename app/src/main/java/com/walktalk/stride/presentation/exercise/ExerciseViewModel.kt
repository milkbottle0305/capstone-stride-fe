package com.walktalk.stride.presentation.exercise

import android.annotation.SuppressLint
import android.location.Location
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.walktalk.stride.data.dto.request.ExerciseRequest
import com.walktalk.stride.data.model.ApiState
import com.walktalk.stride.data.model.Coordinate
import com.walktalk.stride.data.repository.ExerciseRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

class ExerciseViewModel : ViewModel() {
    private val exerciseRepository = ExerciseRepository()

    private val delayMillis: Long = 20000
    private val _saveExerciseApiState = mutableStateOf<ApiState<String>>(ApiState.Empty)
    val saveExerciseApiState: State<ApiState<String>> = _saveExerciseApiState

    // 운동 정보
    private lateinit var startTimeString: String
    private lateinit var endTimeString: String


    private val _pathList = mutableStateOf(listOf<LatLng>())
    val pathList: State<List<LatLng>> = _pathList
    private val _distanceList = mutableStateOf(listOf<Double>())
    val distanceList: State<List<Double>> = _distanceList
    private val _stepList = mutableStateOf(listOf<Int>())
    val stepList: State<List<Int>> = _stepList
    private val _strideList = mutableStateOf(listOf<Int>())
    val strideList: State<List<Int>> = _strideList
    private val _speedList = mutableStateOf(listOf<Double>())
    val speedList: State<List<Double>> = _speedList
    private val _cameraPositionState = mutableStateOf(CameraPositionState())
    val cameraPositionState: State<CameraPositionState> = _cameraPositionState

    // UI
    private val _selectedInfo = mutableStateOf(0)
    val selectedInfo: State<Int> = _selectedInfo
    private val _isShareModalOpen = mutableStateOf(false)
    val isShareModalOpen: State<Boolean> = _isShareModalOpen
    private val _courseName = mutableStateOf("")
    val courseName: State<String> = _courseName
    private val _canComplete = mutableStateOf(false)
    val canComplete: State<Boolean> = _canComplete

    fun setCameraPositionState(cameraPositionState: CameraPositionState) {
        _cameraPositionState.value = cameraPositionState
    }

    fun addPath(latLng: LatLng) {
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
            if (_distanceList.value.isEmpty()) {
                _distanceList.value = _distanceList.value + distance
            } else {
                _distanceList.value = _distanceList.value + (_distanceList.value.last() + distance)
            }
            _speedList.value = _speedList.value + (distance / (delayMillis / 1000) * 3.6)
            if (_stepList.value.last() != 0) {
                _strideList.value =
                    _strideList.value + ((distance * 100) / stepList.value.last()).toInt()
            } else {
                _strideList.value = _strideList.value + 0
            }
            _canComplete.value = true
        }
        _pathList.value = _pathList.value + latLng
    }

    fun addStep(step: Int) {
        _stepList.value = _stepList.value + step
    }

    fun setSelectedInfo(selectedInfo: Int) {
        _selectedInfo.value = selectedInfo
    }

    fun openShareModal() {
        _isShareModalOpen.value = true
    }

    fun closeShareModal() {
        _isShareModalOpen.value = false
        _courseName.value = ""
    }

    fun setCourseName(courseName: String) {
        _courseName.value = courseName
    }

    @SuppressLint("SimpleDateFormat")
    fun startExercise() {
        val startDate = Date()
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
        formatter.timeZone = TimeZone.getTimeZone("Asia/Seoul")
        startTimeString = formatter.format(startDate)
    }

    @SuppressLint("SimpleDateFormat")
    fun completeExercise() {
        val endDate = Date()
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
        formatter.timeZone = TimeZone.getTimeZone("Asia/Seoul")
        endTimeString = formatter.format(endDate)
        val startDate: Date = formatter.parse(startTimeString)

        val diff: Long = endDate.time - startDate.time

        val diffHours = diff / (60 * 60 * 1000)
        val diffMinutes = (diff / (60 * 1000)) % 60
    }

    fun saveExercise(isShareCourse: Boolean) {
        _saveExerciseApiState.value = ApiState.Loading
        viewModelScope.launch {
            try {
                val course = _pathList.value.map { latLng ->
                    Coordinate(
                        latitude = latLng.latitude,
                        longitude = latLng.longitude
                    )
                }
                val request = ExerciseRequest(
                    minStride = _strideList.value.min(),
                    maxStride = _strideList.value.max(),
                    averageStride = _strideList.value.average().toInt(),
                    minSpeed = _speedList.value.min(),
                    maxSpeed = _speedList.value.max(),
                    averageSpeed = _speedList.value.average(),
                    dataCount = _speedList.value.size,
                    step = _stepList.value.sum(),
                    distance = _distanceList.value.last().toInt(),
                    startTime = startTimeString,
                    endTime = endTimeString,
                    stability = 0.0,
                    course = course,
                    doShareCourse = isShareCourse,
                    courseName = _courseName.value
                )
                val response = exerciseRepository.saveExercise(request)
                if (response) {
                    _saveExerciseApiState.value = ApiState.Success("운동 정보 저장에 성공했습니다.")
                } else {
                    _saveExerciseApiState.value = ApiState.Error("운동 정보 저장에 실패했습니다.")
                }
            } catch (e: Exception) {
                _saveExerciseApiState.value =
                    ApiState.Error(e.message ?: "An unknown error occurred")
            }
        }
    }
}