package com.walktalk.stride.presentation.together

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.maps.model.LatLng
import com.walktalk.stride.data.model.ApiState
import com.walktalk.stride.data.model.Coordinate
import com.walktalk.stride.data.model.WaitingRoom
import com.walktalk.stride.data.model.WaitingRooms
import com.walktalk.stride.presentation.exercise.components.SummaryMap
import com.walktalk.stride.presentation.together.components.RoomGrid
import com.walktalk.stride.presentation.together.components.WaitingRoomTopBar
import com.walktalk.stride.ui.theme.StrideTheme

@Composable
fun WaitingRoomScreen(
    navController: NavController,
    viewModel: WaitingRoomViewModel,
    courseId: Int,
) {
    LaunchedEffect(Unit) {
        viewModel.getWaitingRoom(courseId)
    }
    when (viewModel.waitingRoomApiState.value) {
        is ApiState.Loading -> {
            // Show loading indicator
        }

        is ApiState.Success -> {
            WaitingRoomContent(
                navController = navController,
                waitingRooms = viewModel.waitingRooms.value!!
            )
        }

        is ApiState.Error -> {
            // Show error message
        }

        is ApiState.Empty -> {
            // Show empty indicator
        }
    }
}

@Composable
fun WaitingRoomContent(navController: NavController, waitingRooms: WaitingRooms) {
    Scaffold(
        topBar = {
            WaitingRoomTopBar(navController = navController)
        }
    ) { paddingValue ->
        Box(
            modifier = Modifier.padding(paddingValue)
        ) {
            Column(
                modifier = Modifier.padding(
                    horizontal = 10.dp,
                )
            ) {
                Spacer(modifier = Modifier.padding(20.dp))
                Text(
                    text = waitingRooms.courseName,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = StrideTheme.colors.textSecondary
                )
                Spacer(modifier = Modifier.padding(16.dp))
                SummaryMap(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    pathList = waitingRooms.course.map { LatLng(it.latitude, it.longitude) }
                )
                Spacer(modifier = Modifier.padding(16.dp))
                RoomGrid(waitingRooms.rooms)
            }
        }
    }
}

@Preview
@Composable
fun PreviewWaitingRoomScreen() {
    WaitingRoomContent(
        navController = rememberNavController(),
        waitingRooms = WaitingRooms(
            courseName = "Course Name",
            rooms = listOf(
                WaitingRoom(
                    roomId = 1,
                    meetingTime = "",
                    participatingCount = 5,
                ),
                WaitingRoom(
                    roomId = 2,
                    meetingTime = "",
                    participatingCount = 3,
                ),
                WaitingRoom(
                    roomId = 3,
                    meetingTime = "",
                    participatingCount = 2,
                ),
            ),
            course = listOf(
                Coordinate(0.0, 0.0),
                Coordinate(0.0, 0.0),
                Coordinate(0.0, 0.0),
            )
        ),
    )
}