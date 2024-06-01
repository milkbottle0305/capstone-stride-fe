package com.walktalk.stride.presentation.together.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.walktalk.stride.R
import com.walktalk.stride.data.model.WaitingRoom
import com.walktalk.stride.ui.theme.StrideTheme

@Composable
fun RoomGrid(waitingRooms: List<WaitingRoom>) {
    LazyVerticalGrid(
        modifier = Modifier.padding(bottom = 16.dp),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        items(waitingRooms.size) { index ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(15.dp))
                    .background(color = StrideTheme.colors.containerSecondary)
            ) {
                Column(
                    modifier = Modifier.padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.together_detail_time, "12", "00"),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = StrideTheme.colors.textTertiary
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(
                                id = R.string.together_detail_participating,
                                waitingRooms[index].participatingCount
                            ),
                            modifier = Modifier.padding(16.dp),
                            fontSize = 20.sp,
                            color = StrideTheme.colors.containerTextSecondary
                        )
                        Box(
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(10.dp))
                                .background(color = StrideTheme.colors.containerTextSecondary)
                        ) {
                            Text(
                                text = stringResource(id = R.string.together_detail_join),
                                modifier = Modifier.padding(8.dp),
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = StrideTheme.colors.containerSecondary
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun RoomGridPreview() {
    StrideTheme {
        RoomGrid(
            waitingRooms = listOf(
                WaitingRoom(1, "10:00 AM", 5),
                WaitingRoom(1, "10:00 AM", 5),
                WaitingRoom(1, "10:00 AM", 5),
                WaitingRoom(1, "10:00 AM", 5),
                WaitingRoom(1, "10:00 AM", 5),
                WaitingRoom(1, "10:00 AM", 5),
                WaitingRoom(1, "10:00 AM", 5),
            )
        )
    }
}
