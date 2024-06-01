package com.walktalk.stride.presentation.together

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walktalk.stride.data.dto.request.PopularCoursesRequest
import com.walktalk.stride.data.dto.response.toPopularCourses
import com.walktalk.stride.data.model.ApiState
import com.walktalk.stride.data.model.PopularCourse
import com.walktalk.stride.data.repository.TogetherRepository
import kotlinx.coroutines.launch

class TogetherViewModel : ViewModel() {
    private val repository: TogetherRepository = TogetherRepository()

    private val _popularCoursesApiState = mutableStateOf<ApiState<String>>(ApiState.Empty)
    val popularCoursesApiState: State<ApiState<String>> = _popularCoursesApiState

    private val _popularCourses = mutableStateListOf<PopularCourse>()
    val popularCourses: List<PopularCourse> = _popularCourses

    fun getPopularCourses() {
        try {
            _popularCoursesApiState.value = ApiState.Loading
            viewModelScope.launch {
                val request = PopularCoursesRequest(
                    latitude = 37.7749,
                    longitude = 122.4194,
                    showCount = 10,
                    nextCourseId = 1
                )
                val response = repository.getPopularCourses(request)
                _popularCourses.clear()
                _popularCourses.addAll(response.toPopularCourses())
                _popularCoursesApiState.value = ApiState.Success("getPopularCourses Success")
            }
        } catch (e: Exception) {
            _popularCoursesApiState.value = ApiState.Error(e.message ?: "Unknown error")
        }
    }
}