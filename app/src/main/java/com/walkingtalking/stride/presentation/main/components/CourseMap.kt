package com.walkingtalking.stride.presentation.main.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.walkingtalking.stride.data.model.CoursePoint
import com.walkingtalking.stride.ui.theme.StrideTheme

@Composable
fun CourseMap(modifier: Modifier, course: List<CoursePoint>) {
    // List<CoursePoint>를 순회해서 가장 크고 가장 작은 위도값의 중앙값, 가장 크고 가장 작은 경도값의 중앙값을 구해서 startPoint에 저장
    val maxLat = course.maxByOrNull { it.latitude }!!.latitude
    val minLat = course.minByOrNull { it.latitude }!!.latitude
    val medianLat = (maxLat + minLat) / 2

    // 가장 큰 경도 값과 가장 작은 경도 값의 중앙값 계산
    val maxLng = course.maxByOrNull { it.longitude }!!.longitude
    val minLng = course.minByOrNull { it.longitude }!!.longitude
    val medianLng = (maxLng + minLng) / 2

    val startPoint = LatLng(medianLat, medianLng)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(startPoint, 13f)
    }
    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState,
        uiSettings = MapUiSettings(
            scrollGesturesEnabled = false,
            zoomGesturesEnabled = false,
            rotationGesturesEnabled = false,
            tiltGesturesEnabled = false,
            zoomControlsEnabled = false,
        )
    ) {
        Polyline(
            points = course.map { LatLng(it.latitude, it.longitude) },
            color = StrideTheme.colors.error
        )
    }
}
//
//@Preview
//@Composable
//fun CourseMapPreview() {
//    CourseMap(
//        listOf(
//            CoursePoint(37.595, 127.052),
//            CoursePoint(37.5951, 127.0521),
//            CoursePoint(37.5952, 127.0521),
//            CoursePoint(37.5953, 127.0522),
//            CoursePoint(37.5954, 127.0521),
//            CoursePoint(37.5953, 127.0523),
//            CoursePoint(37.5954, 127.0524),
//            CoursePoint(37.5953, 127.0527),
//            CoursePoint(37.5951, 127.053),
//            CoursePoint(37.5949, 127.051),
//            CoursePoint(37.59502, 127.0505),
//        )
//    )
//}
//
