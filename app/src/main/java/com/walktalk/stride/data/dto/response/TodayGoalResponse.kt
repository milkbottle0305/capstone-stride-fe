package com.walktalk.stride.data.dto.response

import com.google.gson.annotations.SerializedName
import com.walktalk.stride.data.model.TodayGoal

data class TodayGoalResponse(
    val tier: Int,
    val exp: Int,
    val stride: TodayIntData,
    val speed: TodayDoubleData,
    val step: TodayIntData,
    @SerializedName("today_tier_up")
    val todayTierUp: Boolean
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
    tier = tier,
    exp = exp,
    currentStride = stride.todayCurrent,
    goalStride = stride.todayGoal,
    currentSpeed = speed.todayCurrent,
    goalSpeed = speed.todayGoal,
    currentStep = step.todayCurrent,
    goalStep = step.todayGoal,
    todayTierUp = todayTierUp
)