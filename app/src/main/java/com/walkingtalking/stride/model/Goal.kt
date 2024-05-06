package com.walkingtalking.stride.model

data class Goal(
    val level: Int,
    val distance: DistanceData,
    val speed: SpeedData,
    val step: StepData,
    val allComplete: Boolean
)

data class DistanceData(
    val todayCurrent: Int,
    val todayGoal: Int
)

data class SpeedData(
    val todayCurrent: Double,
    val todayGoal: Double
)

data class StepData(
    val todayCurrent: Int,
    val todayGoal: Int
)
