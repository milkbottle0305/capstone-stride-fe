package com.walktalk.stride.presentation.exercise.components

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.walktalk.stride.presentation.exercise.ExerciseViewModel
import com.walktalk.stride.ui.theme.StrideTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun ExerciseMap(modifier: Modifier, viewModel: ExerciseViewModel) {
    val context = LocalContext.current
    lateinit var fusedLocationClient: FusedLocationProviderClient

    // 내 위치 가져오기
    var startPoint: LatLng? by remember { mutableStateOf(null) }

    LaunchedEffect(true) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        startLocationUpdates(
            fusedLocationClient = fusedLocationClient,
            context = context,
            viewModel = viewModel,
            onLocationReceived = { location ->
                startPoint = LatLng(location.latitude, location.longitude)
            }
        )
    }

    // 카메라 포지션 설정
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(startPoint ?: LatLng(0.0, 0.0), 15f)
    }

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState,
        properties = MapProperties(isMyLocationEnabled = true)
    ) {
        startPoint?.let { startPoint ->
            // 시작 지점이 있을 때 폴리라인 그리기
            Polyline(
                points = viewModel.pathList,
                color = StrideTheme.colors.error,
                width = 2f
            )
        }
    }
}

private fun startLocationUpdates(
    fusedLocationClient: FusedLocationProviderClient,
    context: Context,
    viewModel: ExerciseViewModel,
    onLocationReceived: (android.location.Location) -> Unit
) {
    val locationRequest = LocationRequest.create().apply {
        interval = 20000 // 20초마다
        fastestInterval = 20000
        priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
    }

    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            locationResult.locations.forEach { location ->
                CoroutineScope(Dispatchers.IO).launch {
                    withContext(Dispatchers.Main) {
                        viewModel.addPath(LatLng(location.latitude, location.longitude))
                        // 위치를 가져올 때마다 호출되는 콜백 함수
                        onLocationReceived(location)
                    }
                }
            }
        }
    }

    // 위치 권한 확인
    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        return
    }

    // 위치 업데이트 요청
    fusedLocationClient.requestLocationUpdates(
        locationRequest,
        locationCallback,
        Looper.getMainLooper()
    )
}