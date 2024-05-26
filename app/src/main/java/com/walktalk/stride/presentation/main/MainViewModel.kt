package com.walktalk.stride.presentation.main

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walktalk.stride.data.dto.response.toMyRooms
import com.walktalk.stride.data.dto.response.toRecentCourses
import com.walktalk.stride.data.dto.response.toTodayGoal
import com.walktalk.stride.data.model.ApiState
import com.walktalk.stride.data.model.MyRoom
import com.walktalk.stride.data.model.RecentCourse
import com.walktalk.stride.data.model.TodayGoal
import com.walktalk.stride.data.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val mainRepository = MainRepository()

    private val _allApiState = mutableStateOf<ApiState<String>>(ApiState.Empty)
    val allApiState: State<ApiState<String>> = _allApiState
    private val _goalApiState = mutableStateOf<ApiState<String>>(ApiState.Empty)
    val goalApiState: State<ApiState<String>> = _goalApiState
    private val _recentApiState = mutableStateOf<ApiState<String>>(ApiState.Empty)
    val recentApiState: State<ApiState<String>> = _recentApiState
    private val _myRoomsApiState = mutableStateOf<ApiState<String>>(ApiState.Empty)
    val myRoomsApiState: State<ApiState<String>> = _myRoomsApiState

    private val _size = mutableStateOf(0.dp)
    val size: State<Dp> = _size
    private val _offsetX = mutableStateOf(0.dp)
    val offsetX: State<Dp> = _offsetX
    private val _offsetY = mutableStateOf(0.dp)
    val offsetY: State<Dp> = _offsetY
    private val _isGoalClicked = mutableStateOf(false)
    val isGoalClicked: State<Boolean> = _isGoalClicked
    private val _isExerciseClicked = mutableStateOf(false)
    val isExerciseClicked: State<Boolean> = _isExerciseClicked

    private val _todayGoal = mutableStateOf<TodayGoal?>(null)
    val todayGoal: State<TodayGoal?> = _todayGoal
    private val _recentCourse = mutableStateOf<RecentCourse?>(null)
    val recentCourse: State<RecentCourse?> = _recentCourse
    private val _recentCourses = mutableStateOf<List<RecentCourse>>(emptyList())
    val recentCourses: State<List<RecentCourse>> = _recentCourses
    private val _myRooms = mutableStateOf<List<MyRoom>>(emptyList())
    val myRooms: State<List<MyRoom>> = _myRooms


    private fun updateAllApiState() {
        if (_goalApiState.value is ApiState.Success &&
            _recentApiState.value is ApiState.Success &&
            _myRoomsApiState.value is ApiState.Success
        ) {
            _allApiState.value = ApiState.Success("All API calls succeeded")
        } else if (_goalApiState.value is ApiState.Error ||
            _recentApiState.value is ApiState.Error ||
            _myRoomsApiState.value is ApiState.Error
        ) {
            _allApiState.value = ApiState.Error("One or more API calls failed")
        }
    }

    fun getTodayGoal() {
        Log.d("MainViewModel", "getTodayGoal")
        _goalApiState.value = ApiState.Loading
        viewModelScope.launch {
            try {
                val response = mainRepository.getTodayGoal()
                _todayGoal.value = response.toTodayGoal()
                _goalApiState.value = ApiState.Success("getTodayGoal Success")
            } catch (e: Exception) {
                _goalApiState.value = ApiState.Error(e.message ?: "Unknown error")
            } finally {
                updateAllApiState()
            }
        }
    }

    fun getRecentCourses() {
        _recentApiState.value = ApiState.Loading
        viewModelScope.launch {
            try {
                val response = mainRepository.getRecentCourses()
                _recentCourses.value = response.toRecentCourses()
                if (_recentCourses.value.isNotEmpty())
                    _recentCourse.value = _recentCourses.value[0]
                _recentApiState.value = ApiState.Success("getRecentCourses Success")
            } catch (e: Exception) {
                _recentApiState.value = ApiState.Error(e.message ?: "Unknown error")
            } finally {
                updateAllApiState()
            }
        }
    }

    fun getMyRooms() {
        _myRoomsApiState.value = ApiState.Loading
        viewModelScope.launch {
            try {
                val response = mainRepository.getMyRooms()
                _myRooms.value = response.toMyRooms()
                _myRoomsApiState.value = ApiState.Success("getMyRooms Success")
            } catch (e: Exception) {
                _myRoomsApiState.value = ApiState.Error(e.message ?: "Unknown error")
            } finally {
                updateAllApiState()
            }
        }
    }

    fun setIsGoalClicked(value: Boolean) {
        _isGoalClicked.value = value
    }

    fun setIsExerciseClicked(value: Boolean) {
        _isExerciseClicked.value = value
    }

    fun setSize(value: Dp) {
        _size.value = value
    }

    fun setOffsetX(value: Dp) {
        _offsetX.value = value
    }

    fun setOffsetY(value: Dp) {
        _offsetY.value = value
    }
}