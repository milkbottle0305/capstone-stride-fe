package com.walktalk.stride.presentation.exercise

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.walktalk.stride.R
import com.walktalk.stride.presentation.exercise.components.ExerciseMap
import com.walktalk.stride.presentation.exercise.components.StopButton

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ExerciseScreen(
    navController: NavController,
    viewModel: ExerciseViewModel,
    exerciseType: String
) {
    val context = LocalContext.current
    val permissionErrorString = stringResource(id = R.string.permission_error)

    val permissions = listOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
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

        LaunchedEffect(permissionState) {
            requestPermissionLauncher.launch(permissions.toTypedArray())
        }
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
            ExerciseMap(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 150.dp),
                viewModel = viewModel
            )
            Spacer(modifier = Modifier.height(16.dp))
            StopButton(modifier = Modifier.align(Alignment.End), viewModel = viewModel)
        }
    }
}


