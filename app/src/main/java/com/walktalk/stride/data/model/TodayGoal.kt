package com.walktalk.stride.data.model

data class TodayGoal(
    val level: Int,
    val currentDistance: Int,
    val goalDistance: Int,
    val currentSpeed: Double,
    val goalSpeed: Double,
    val currentStep: Int,
    val goalStep: Int,
    val allComplete: Boolean
)
