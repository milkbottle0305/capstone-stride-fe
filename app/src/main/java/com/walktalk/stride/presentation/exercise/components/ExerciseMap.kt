package com.walktalk.stride.presentation.exercise.components

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Polyline
import com.walktalk.stride.ExerciseService
import com.walktalk.stride.presentation.exercise.ExerciseViewModel
import com.walktalk.stride.ui.theme.StrideTheme

@Composable
fun ExerciseMap(modifier: Modifier, viewModel: ExerciseViewModel) {
    val context = LocalContext.current
    val filter = IntentFilter().apply {
        addAction("STEP_UPDATE")
        addAction("LOCATION_UPDATE")
    }
    val intent = Intent(context, ExerciseService::class.java)
    val intentService = remember { Intent(context, ExerciseService::class.java) }


    val cameraPositionState = viewModel.cameraPositionState.value
    val pathList = viewModel.pathList.value

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
        onDispose {
            context.stopService(intentService)
            context.unregisterReceiver(broadcastReceiver)
        }
    }

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState,
        properties = MapProperties(isMyLocationEnabled = true),
    ) {
        Polyline(
            points = pathList,
            color = StrideTheme.colors.error,
            width = 2f
        )
    }
}