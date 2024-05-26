package com.walktalk.stride.data.dto.response

import com.google.gson.annotations.SerializedName
import com.walktalk.stride.data.model.TodayGoal

data class TodayGoalResponse(
    val level: Int,
    val distance: TodayIntData,
    val speed: TodayDoubleData,
    val step: TodayIntData,
    @SerializedName("all_complete")
    val allComplete: Boolean
)

data class TodayIntData(
    @SerializedName("today_current")
    val todayCurrent: Int,
    @SerializedName("today_goal")
    val todayGoal: Int
)

data class TodayDoubleData(
    @SerializedName("today_current")
    val todayCurrent: Double,
    @SerializedName("today_goal")
    val todayGoal: Double
)

fun TodayGoalResponse.toTodayGoal() = TodayGoal(
    level = level,
    currentDistance = distance.todayCurrent,
    goalDistance = distance.todayGoal,
    currentSpeed = speed.todayCurrent,
    goalSpeed = speed.todayGoal,
    currentStep = step.todayCurrent,
    goalStep = step.todayGoal,
    allComplete = allComplete
)