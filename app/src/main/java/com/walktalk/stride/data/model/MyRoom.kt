package com.walktalk.stride.data.model

data class MyRoom(
    val roomName: String,
    val courseName: String,
    val participatingCount: Int,
    val course: List<Coordinate>
)
