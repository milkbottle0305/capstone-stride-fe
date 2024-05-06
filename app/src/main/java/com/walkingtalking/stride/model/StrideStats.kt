package com.walkingtalking.stride.model

import com.google.gson.annotations.SerializedName

data class StrideStats(
    val mine: List<StrideRecord>,
    @SerializedName("age_group") val ageGroup: List<StrideRecord>
)

data class StrideRecord(
    @SerializedName("min_stride") val minStride: Int,
    @SerializedName("max_stride") val maxStride: Int,
    @SerializedName("average_stride") val averageStride: Int,
    val stability: Double,
    val date: String
)
