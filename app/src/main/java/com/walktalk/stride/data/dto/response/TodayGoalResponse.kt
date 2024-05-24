package com.walktalk.stride.data.dto.response

import com.google.gson.annotations.SerializedName

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