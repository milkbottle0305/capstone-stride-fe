package com.walktalk.stride.presentation.together.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.walktalk.stride.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WaitingRoomTopBar(navController: NavController) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.together_together)
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "back")
            }
        },
        actions = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.Default.Add, contentDescription = "create_room")
            }
        }
    )
}
