package com.walktalk.stride.data.dto.response

import com.google.gson.annotations.SerializedName

data class SpeedStatsResponse(
    val mine: List<SpeedSummary>,
    @SerializedName("age_group")
    val ageGroup: List<SpeedSummary>
)

data class SpeedSummary(
    @SerializedName("min_speed")
    val minSpeed: Double,
    @SerializedName("max_speed")
    val maxSpeed: Double,
    @SerializedName("average_speed")
    val averageSpeed: Double,
    val date: String
)