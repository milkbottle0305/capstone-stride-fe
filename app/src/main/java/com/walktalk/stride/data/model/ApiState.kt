package com.walktalk.stride.data.model

sealed class ApiState<out T> {
    data class Success<T>(val data: T) : ApiState<T>()
    data class Error(val message: String) : ApiState<Nothing>()
    object Loading : ApiState<Nothing>()
    object Empty : ApiState<Nothing>()
}
