package com.walktalk.stride.presentation.exercise

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.walktalk.stride.R
import com.walktalk.stride.data.model.ApiState
import com.walktalk.stride.presentation.exercise.components.ShareButtonRow
import com.walktalk.stride.presentation.exercise.components.ShareModal
import com.walktalk.stride.presentation.exercise.components.SummaryInfoButton
import com.walktalk.stride.presentation.exercise.components.SummaryInfoChart
import com.walktalk.stride.presentation.exercise.components.SummaryMap
import com.walktalk.stride.presentation.navigation.Screen
import com.walktalk.stride.ui.theme.StrideTheme

@Composable
fun ExerciseSummaryScreen(navController: NavController, viewModel: ExerciseViewModel) {
    val context = LocalContext.current
    val avgSpeed = viewModel.speedList.value.average()
    val avgStride = viewModel.strideList.value.average().toInt()
    val totalDistance = viewModel.distanceList.value.last().toInt()
    val totalStep = viewModel.stepList.value.sum()
    val selectedInfo = viewModel.selectedInfo.value
    val scrollState = rememberScrollState()

    val isShareModalOpen = viewModel.isShareModalOpen.value
    val courseName = viewModel.courseName.value
    val saveExerciseApiState = viewModel.saveExerciseApiState.value
    LaunchedEffect(saveExerciseApiState) {
        when (saveExerciseApiState) {
            is ApiState.Success -> {
                navController.navigate(Screen.Main.route) {
                    popUpTo(Screen.Main.route) {
                        inclusive = true
                    }
                }
            }

            is ApiState.Error -> {
                Toast.makeText(context, saveExerciseApiState.message, Toast.LENGTH_SHORT).show()
            }

            is ApiState.Loading -> {
            }

            is ApiState.Empty -> {
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = StrideTheme.colors.backgroundPrimary)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SummaryMap(viewModel = viewModel)
        Spacer(modifier = Modifier.height(22.dp))
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Row {
                SummaryInfoButton(
                    modifier = Modifier.weight(1f), title = stringResource(id = R.string.speed),
                    description = stringResource(
                        id = R.string.speed_unit, avgSpeed
                    ),
                    isClicked = selectedInfo == 0,
                ) {
                    viewModel.setSelectedInfo(0)
                }
                Spacer(modifier = Modifier.width(17.dp))
                SummaryInfoButton(
                    modifier = Modifier.weight(1f), title = stringResource(id = R.string.stride),
                    description = stringResource(
                        id = R.string.stride_unit, avgStride
                    ),
                    isClicked = selectedInfo == 1,
                ) {
                    viewModel.setSelectedInfo(1)
                }
            }
            Row {
                SummaryInfoButton(
                    modifier = Modifier.weight(1f), title = stringResource(id = R.string.distance),
                    description = stringResource(
                        id = R.string.distance_unit, totalDistance
                    ),
                    isClicked = selectedInfo == 2,
                ) {
                    viewModel.setSelectedInfo(2)
                }
                Spacer(modifier = Modifier.width(17.dp))
                SummaryInfoButton(
                    modifier = Modifier.weight(1f), title = stringResource(id = R.string.step),
                    description = stringResource(
                        id = R.string.step_unit, totalStep
                    ),
                    isClicked = selectedInfo == 3,
                ) {
                    viewModel.setSelectedInfo(3)
                }
            }

        }
        Spacer(modifier = Modifier.height(20.dp))
        when (selectedInfo) {
            0 -> SummaryInfoChart(
                data = viewModel.speedList.value
            )

            1 -> SummaryInfoChart(
                data = viewModel.strideList.value
            )

            2 -> SummaryInfoChart(
                data = viewModel.distanceList.value
            )

            3 -> SummaryInfoChart(
                data = viewModel.stepList.value
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            stringResource(id = R.string.exercise_share),
            color = StrideTheme.colors.textPrimary,
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        ShareButtonRow(
            onNoClick = {
                viewModel.saveExercise(false)
            },
            onYesClick = {
                viewModel.openShareModal()
            }
        )
    }

    if (isShareModalOpen) {
        ShareModal(
            inputText = courseName,
            enabled = courseName.isNotEmpty() && saveExerciseApiState !is ApiState.Loading,
            onTextChange = { viewModel.setCourseName(it) },
            onCancelClick = { viewModel.closeShareModal() },
            onInputSubmit = { viewModel.saveExercise(true) }
        )
    }
}

