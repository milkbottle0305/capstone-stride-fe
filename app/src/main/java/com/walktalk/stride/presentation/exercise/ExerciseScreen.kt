package com.walktalk.stride.presentation.exercise

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.walktalk.stride.ExerciseService
import com.walktalk.stride.R
import com.walktalk.stride.presentation.exercise.components.CompleteButton
import com.walktalk.stride.presentation.exercise.components.ExerciseMap
import com.walktalk.stride.presentation.exercise.components.ProgressBar
import com.walktalk.stride.presentation.navigation.Screen
import com.walktalk.stride.ui.theme.StrideTheme

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ExerciseScreen(
    navController: NavController,
    viewModel: ExerciseViewModel,
    exerciseType: String,
    goalStep: Int
) {
    val context = LocalContext.current
    val permissionErrorString = stringResource(id = R.string.permission_error)
    val filter = IntentFilter().apply {
        addAction("STEP_UPDATE")
        addAction("LOCATION_UPDATE")
    }
    val intent = Intent(context, ExerciseService::class.java)
    val intentService = remember { Intent(context, ExerciseService::class.java) }

    val canComplete = viewModel.canComplete.value
    val cameraPositionState = viewModel.cameraPositionState.value
    val pathList = viewModel.pathList.value
    val currentStep = viewModel.stepList.value.sum()

    val broadcastReceiver = remember {
        object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                Log.d("ExerciseMap", "onReceive: ${intent?.action}")
                when (intent?.action) {
                    "STEP_UPDATE" -> {
                        val step = intent.getIntExtra("step", 0)
                        viewModel.addStep(step)
                    }

                    "LOCATION_UPDATE" -> {
                        val lat = intent.getDoubleExtra("lat", 0.0)
                        val lng = intent.getDoubleExtra("lng", 0.0)
                        viewModel.addPath(LatLng(lat, lng))
                        viewModel.setCameraPositionState(
                            CameraPositionState(
                                CameraPosition.fromLatLngZoom(
                                    LatLng(lat, lng),
                                    17f
                                )
                            )
                        )
                    }
                }
            }
        }
    }

    DisposableEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.registerReceiver(broadcastReceiver, filter)
            context.startForegroundService(intent)
        } else {
            context.registerReceiver(broadcastReceiver, filter)
            context.startService(intentService)
        }
        viewModel.startExercise()
        onDispose {
            context.stopService(intentService)
            context.unregisterReceiver(broadcastReceiver)
        }
    }


    val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_BACKGROUND_LOCATION,
            Manifest.permission.ACTIVITY_RECOGNITION,
        )
    } else {
        listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        )
    }
    // 위치 권한 상태를 관리합니다.
    val permissionState = rememberMultiplePermissionsState(
        permissions = permissions
    )

    // 위치 권한이 허용되지 않았을 때만 권한 요청을 실행합니다.
    if (!permissionState.allPermissionsGranted) {
        val requestPermissionLauncher =
            rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissionsMap ->
                val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
                if (!areGranted) {
                    Toast.makeText(context, permissionErrorString, Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                    // 위치 권한이 거부되었을 때 설정으로 이동하는 인텐트 생성
                    val settingsIntent = Intent().apply {
                        action = android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                        data = Uri.fromParts("package", context.packageName, null)
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                    // 설정으로 이동
                    context.startActivity(settingsIntent)
                }
            }

        LaunchedEffect(Unit) {
            requestPermissionLauncher.launch(permissions.toTypedArray())
        }
    } else {
        ExerciseContent(
            canComplete = canComplete,
            cameraPositionState = cameraPositionState,
            pathList = pathList,
            currentDistance = currentStep,
            goalDistance = goalStep,
            onCompleteExercise = {
                viewModel.completeExercise()
                navController.navigate(Screen.ExerciseSummary.route) {
                    popUpTo(Screen.Exercise.route) {
                        inclusive = true
                    }
                }
            }
        )
    }
}

@Composable
fun ExerciseContent(
    canComplete: Boolean,
    cameraPositionState: CameraPositionState,
    pathList: List<LatLng>,
    currentDistance: Int,
    goalDistance: Int,
    onCompleteExercise: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = StrideTheme.colors.surface,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ExerciseMap(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.8f),
                cameraPositionState = cameraPositionState,
                pathList = pathList
            )
            Column(
                modifier = Modifier.weight(0.2f)
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                ProgressBar(currentDistance, goalDistance)
                Spacer(modifier = Modifier.height(20.dp))
                CompleteButton(
                    modifier = Modifier
                        .align(Alignment.End),
                    enabled = canComplete
                ) {
                    onCompleteExercise()
                }
            }
        }
    }
}