package com.walkingtalking.stride.data.dto

data class LoginResponse(
    val access_token: String,
    val refresh_token: String,
    val need_initialization: Boolean
)