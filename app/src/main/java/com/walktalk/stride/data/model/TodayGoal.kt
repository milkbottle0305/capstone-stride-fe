package com.walktalk.stride.data.model

data class TodayGoal(
    val tier: Int,
    val exp: Int,
    val currentStride: Int,
    val goalStride: Int,
    val currentSpeed: Double,
    val goalSpeed: Double,
    val currentStep: Int,
    val goalStep: Int,
    val todayTierUp: Boolean
)
