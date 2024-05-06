package com.walkingtalking.stride.model

import com.google.gson.annotations.SerializedName

data class SpeedStats(
    val mine: List<SpeedRecord>,
    @SerializedName("age_group") val ageGroup: List<SpeedRecord>
)

data class SpeedRecord(
    @SerializedName("min_speed") val minSpeed: Double,
    @SerializedName("max_speed") val maxSpeed: Double,
    @SerializedName("average_speed") val averageSpeed: Double,
    val date: String
)
