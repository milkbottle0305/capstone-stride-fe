package com.walktalk.stride.presentation.together.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Polyline
import com.walktalk.stride.R
import com.walktalk.stride.data.model.Coordinate
import com.walktalk.stride.ui.theme.StrideTheme

@Composable
fun RoomCard(
    pathList: List<Coordinate>,
    roomName: String,
    participatingCount: Int,
    courseDistance: Double
) {
    val maxLat = pathList.maxByOrNull { it.latitude }?.latitude ?: 0.0
    val minLat = pathList.minByOrNull { it.latitude }?.latitude ?: 0.0
    val maxLng = pathList.maxByOrNull { it.longitude }?.longitude ?: 0.0
    val minLng = pathList.minByOrNull { it.longitude }?.longitude ?: 0.0
    val centerLat = (maxLat + minLat) / 2
    val centerLng = (maxLng + minLng) / 2
    val cameraPositionState = CameraPositionState(
        CameraPosition.fromLatLngZoom(
            LatLng(centerLat, centerLng),
            15f
        )
    )
    val points = pathList.map { coordinate ->
        LatLng(coordinate.latitude, coordinate.longitude)
    }
    Surface(
        modifier = Modifier.border(
            1.dp,
            color = StrideTheme.colors.buttonBorderSecondary,
            shape = RoundedCornerShape(10.dp)
        ).clip(RoundedCornerShape(10.dp)),
        color = StrideTheme.colors.buttonSecondary,
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            GoogleMap(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(124.dp),
                cameraPositionState = cameraPositionState,
                uiSettings = MapUiSettings(
                    zoomControlsEnabled = false,
                    myLocationButtonEnabled = false,
                    compassEnabled = false,
                    rotationGesturesEnabled = false,
                    scrollGesturesEnabled = false,
                    tiltGesturesEnabled = false,
                    zoomGesturesEnabled = false
                )
            ) {
                Polyline(
                    points = points,
                    color = StrideTheme.colors.error,
                    width = 5f
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = roomName,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = StrideTheme.colors.textPrimary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.together_participating, participatingCount),
                    fontSize = 16.sp,
                    color = StrideTheme.colors.textPrimary
                )
                Image(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "user",
                    modifier = Modifier
                        .height(20.dp)
                        .width(20.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.align(Alignment.End),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.distance),
                    contentDescription = "distance",
                    modifier = Modifier
                        .height(20.dp)
                        .width(20.dp)
                )
                Text(
                    text = stringResource(id = R.string.together_distance, courseDistance),
                    fontSize = 16.sp,
                    color = StrideTheme.colors.textPrimary
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Row {
                Surface(
                    modifier = Modifier
                        .border(
                            1.dp,
                            color = StrideTheme.colors.buttonTextSecondary,
                            shape = RoundedCornerShape(8.dp),
                        )
                        .clip(RoundedCornerShape(8.dp)),
                    color = StrideTheme.colors.buttonSecondary,
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(
                        text = stringResource(id = R.string.together_single),
                        fontSize = 16.sp,
                        color = StrideTheme.colors.textPrimary,
                        modifier = Modifier.padding(horizontal = 5.dp, vertical = 12.dp)
                    )
                }
                Spacer(
                    modifier = Modifier.weight(1f)
                )
                Surface(
                    modifier = Modifier
                        .border(
                            1.dp,
                            color = StrideTheme.colors.buttonTextSecondary,
                            shape = RoundedCornerShape(8.dp),
                        )
                        .clip(RoundedCornerShape(8.dp)),
                    color = StrideTheme.colors.buttonSecondary,
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(
                        text = stringResource(id = R.string.together_together),
                        fontSize = 16.sp,
                        color = StrideTheme.colors.textPrimary,
                        modifier = Modifier.padding(horizontal = 5.dp, vertical = 12.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun RoomCardPreview() {
    RoomCard(
        pathList = listOf(),
        roomName = "Room Name",
        participatingCount = 0,
        courseDistance = 0.0
    )
}