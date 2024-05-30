package com.walktalk.stride.presentation.together

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.walktalk.stride.data.model.ApiState

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
                waitingRoom = viewModel.waitingRoom
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