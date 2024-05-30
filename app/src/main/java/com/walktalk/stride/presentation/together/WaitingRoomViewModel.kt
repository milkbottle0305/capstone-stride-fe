package com.walktalk.stride.presentation.together

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walktalk.stride.data.dto.response.toWaitingRooms
import com.walktalk.stride.data.model.ApiState
import com.walktalk.stride.data.model.WaitingRooms
import com.walktalk.stride.data.repository.WaitingRoomRepository
import kotlinx.coroutines.launch

class WaitingRoomViewModel : ViewModel() {
    private val _repository = WaitingRoomRepository()

    private val _waitingRoomApiState = mutableStateOf<ApiState<String>>(ApiState.Empty)
    val waitingRoomApiState: State<ApiState<String>> = _waitingRoomApiState

    private val _waitingRoom = mutableStateOf<WaitingRooms?>(null)
    val waitingRoom: State<WaitingRooms?> = _waitingRoom

    fun getWaitingRoom(courseId: Int) {
        try {
            _waitingRoomApiState.value = ApiState.Loading
            viewModelScope.launch {
                val response = _repository.getWaitingRooms(1)
                _waitingRoom.value = response.toWaitingRooms()
                _waitingRoomApiState.value = ApiState.Success("getWaitingRoom Success")
            }
        } catch (e: Exception) {
            _waitingRoomApiState.value = ApiState.Error(e.message ?: "Unknown error")
        }
    }
}