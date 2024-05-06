package com.walkingtalking.stride.ui.main

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.walkingtalking.stride.R
import com.walkingtalking.stride.data.model.CoursePoint
import com.walkingtalking.stride.ui.main.components.CourseMap
import com.walkingtalking.stride.ui.main.components.GoalModal
import com.walkingtalking.stride.ui.theme.StrideTheme

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = viewModel()
) {

    val size = remember { mutableStateOf(0.dp) }
    val offsetX = remember { mutableStateOf(0.dp) }
    val offsetY = remember { mutableStateOf(0.dp) }
    val isGoalClicked = remember { mutableStateOf(false) }
    val isExerciseClicked = remember { mutableStateOf(false) }
    val density = LocalDensity.current

    val level: Int = 1
    val currentDistance: Int = 3500
    val goalDistance: Int = 5000
    val currentSpeed: Double = 4.8
    val goalSpeed: Double = 5.0
    val currentStep: Int = 4000
    val goalStep: Int = 5000

    data class Room(
        val roomName: String,
        val courseName: String,
        val memberCount: Int,
        val paths: List<CoursePoint>
    )

    data class Course(
        val courseName: String,
        val distance: Int,
        val duration: String,
        val paths: List<CoursePoint>
    )

    val course = Course(
        "경희대 국제캠 한바퀴", 3500, "1시간 30분", listOf(
            CoursePoint(37.595, 127.052),
            CoursePoint(37.5951, 127.0521),
            CoursePoint(37.5952, 127.0522),
            CoursePoint(37.5953, 127.0523),
            CoursePoint(37.5954, 127.0524),
            CoursePoint(37.5955, 127.0525),
            CoursePoint(37.5956, 127.0526),
            CoursePoint(37.5957, 127.0527),
            CoursePoint(37.5958, 127.0528),
            CoursePoint(37.5959, 127.0529),
            CoursePoint(37.596, 127.053),
        )
    )

    val roomList = listOf(
        Room(
            "경희대 사랑모임",
            "경희대 국제캠 한바퀴 코스",
            3,
            listOf(
                CoursePoint(37.595, 127.052),
                CoursePoint(37.5951, 127.0521),
                CoursePoint(37.5952, 127.0522),
                CoursePoint(37.5953, 127.0523),
                CoursePoint(37.5954, 127.0524),
                CoursePoint(37.5955, 127.0525),
                CoursePoint(37.5956, 127.0526),
                CoursePoint(37.5957, 127.0527),
                CoursePoint(37.5958, 127.0528),
                CoursePoint(37.5959, 127.0529),
                CoursePoint(37.596, 127.053),
            )
        ),
        Room(
            "경희대 사랑모임",
            "경희대 국제캠 한바퀴 코스경희대 국제캠 한바퀴 코스경희대 국제캠 한바퀴 코스경희대 국제캠 한바퀴 코스",
            3,
            listOf(
                CoursePoint(37.595, 127.052),
                CoursePoint(37.5951, 127.0521),
                CoursePoint(37.5952, 127.0522),
                CoursePoint(37.5953, 127.0523),
                CoursePoint(37.5954, 127.0524),
                CoursePoint(37.5955, 127.0525),
                CoursePoint(37.5956, 127.0526),
                CoursePoint(37.5957, 127.0527),
                CoursePoint(37.5958, 127.0528),
                CoursePoint(37.5959, 127.0529),
                CoursePoint(37.596, 127.053),
            )
        ),
        Room(
            "경희대 사랑모임",
            "경희대 국제캠 한바퀴 코스",
            3,
            listOf(
                CoursePoint(37.595, 127.052),
                CoursePoint(37.5951, 127.0521),
                CoursePoint(37.5952, 127.0522),
                CoursePoint(37.5953, 127.0523),
                CoursePoint(37.5954, 127.0524),
                CoursePoint(37.5955, 127.0525),
                CoursePoint(37.5956, 127.0526),
                CoursePoint(37.5957, 127.0527),
                CoursePoint(37.5958, 127.0528),
                CoursePoint(37.5959, 127.0529),
                CoursePoint(37.596, 127.053),
            )
        ),
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = StrideTheme.colors.backgroundPrimary
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(id = R.string.main_today_goal),
                    color = StrideTheme.colors.textSecondary,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(20.dp))
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { isGoalClicked.value = true },
                    color = StrideTheme.colors.backgroundSecondary,
                    shape = RoundedCornerShape(16.dp),
                    shadowElevation = 4.dp
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 18.dp, vertical = 12.dp)
                    ) {
                        Text(
                            text = "Lv $level",
                            color = StrideTheme.colors.textSecondary,
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "%,d".format(currentDistance),
                                    color = StrideTheme.colors.textPrimary,
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                                Text(
                                    text = stringResource(R.string.distance_unit, goalDistance),
                                    color = StrideTheme.colors.textPrimary,
                                    fontSize = 12.sp,
                                )
                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "%.1f".format(currentSpeed),
                                    color = StrideTheme.colors.textPrimary,
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                                Text(
                                    text = stringResource(R.string.speed_unit, goalSpeed),
                                    color = StrideTheme.colors.textPrimary,
                                    fontSize = 12.sp,
                                )
                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "%,d".format(currentStep),
                                    color = StrideTheme.colors.textPrimary,
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                                Text(
                                    text = stringResource(R.string.step_unit, goalStep),
                                    color = StrideTheme.colors.textPrimary,
                                    fontSize = 12.sp,
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = stringResource(id = R.string.main_join_room),
                    color = StrideTheme.colors.textSecondary,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(20.dp))
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    items(roomList.size) {
                        Surface(
                            color = StrideTheme.colors.backgroundSecondary,
                            shape = RoundedCornerShape(10.dp),
                            shadowElevation = 4.dp,
                        ) {
                            Row(
                                modifier = Modifier.padding(8.dp)
                            ) {
                                val density = LocalDensity.current
                                CourseMap(
                                    modifier = Modifier
                                        .height(size.value)
                                        .width(size.value)
                                        .clip(RoundedCornerShape(10.dp)),
                                    course = roomList[it].paths
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Column(modifier = Modifier
                                    .width(200.dp)
                                    .onSizeChanged {
                                        with(density) {
                                            size.value = it.height.toDp()
                                        }
                                    }
                                ) {
                                    Text(
                                        text = roomList[it].roomName,
                                        color = StrideTheme.colors.textSecondary,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        maxLines = 1,
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = roomList[it].courseName,
                                        color = StrideTheme.colors.textSecondary,
                                        fontSize = 15.sp,
                                        maxLines = 1,
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = stringResource(
                                            R.string.together_participating,
                                            roomList[it].memberCount
                                        ),
                                        color = StrideTheme.colors.textSecondary,
                                        fontSize = 15.sp,
                                    )
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = stringResource(id = R.string.main_current_course),
                    color = StrideTheme.colors.textSecondary,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(20.dp))
                Surface(
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = StrideTheme.colors.backgroundSecondary,
                    shape = RoundedCornerShape(16.dp),
                    shadowElevation = 4.dp
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        CourseMap(
                            modifier = Modifier
                                .width(size.value)
                                .height(size.value),
                            course = course.paths
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = course.courseName,
                                color = StrideTheme.colors.textSecondary,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = stringResource(
                                    R.string.main_course_distance,
                                    course.distance
                                ),
                                color = StrideTheme.colors.textSecondary,
                                fontSize = 12.sp,
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = stringResource(
                                    R.string.main_course_duration,
                                    course.duration
                                ),
                                color = StrideTheme.colors.textSecondary,
                                fontSize = 12.sp,
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(250.dp))
            }
        }
        if (isExerciseClicked.value) {
            // 클릭 상태일 때, 새로운 버튼 생성
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }) {
                        isExerciseClicked.value = false
                    },
                color = Color.Black.copy(alpha = 0.5f),
            ) {}
            Surface(
                modifier = Modifier
                    .size(100.dp)
                    .offset(x = offsetX.value, y = offsetY.value + (-200).dp),
                shape = CircleShape,
                color = StrideTheme.colors.buttonPrimary,
            ) {
                Text(
                    modifier = Modifier.wrapContentSize(Alignment.Center),
                    text = stringResource(id = R.string.main_exercise_walk),
                    color = StrideTheme.colors.buttonTextPrimary,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            Surface(
                modifier = Modifier
                    .size(100.dp)
                    .offset(x = offsetX.value + (-130).dp, y = offsetY.value + (-100).dp),
                shape = CircleShape,
                color = StrideTheme.colors.buttonPrimary,
            ) {
                Text(
                    modifier = Modifier.wrapContentSize(Alignment.Center),
                    text = stringResource(id = R.string.main_exercise_speed),
                    color = StrideTheme.colors.buttonTextPrimary,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            Surface(
                modifier = Modifier
                    .size(100.dp)
                    .offset(x = offsetX.value + 130.dp, y = offsetY.value + (-100).dp),
                shape = CircleShape,
                color = StrideTheme.colors.buttonPrimary,
            ) {
                Text(
                    modifier = Modifier.wrapContentSize(Alignment.Center),
                    text = stringResource(id = R.string.main_exercise_stride),
                    color = StrideTheme.colors.buttonTextPrimary,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 30.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Surface(
                modifier = Modifier
                    .size(100.dp)
                    .clickable { isExerciseClicked.value = !isExerciseClicked.value }
                    .onGloballyPositioned { coordinates ->
                        val position = coordinates.positionInWindow()
                        with(density) {
                            offsetX.value = position.x.toDp()
                            offsetY.value = position.y.toDp()
                        }
                        Log.d("offset", "offsetX: ${offsetX.value}, offsetY: ${offsetY.value}")
                    },
                shape = CircleShape,
                color = StrideTheme.colors.buttonPrimary,
            ) {
                Text(
                    modifier = Modifier
                        .wrapContentSize(Alignment.Center),
                    text = if (isExerciseClicked.value) stringResource(id = R.string.main_exercise_cancel) else stringResource(
                        id = R.string.main_exercise
                    ),
                    color = StrideTheme.colors.buttonTextPrimary,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
        if (isGoalClicked.value) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }) {
                        isGoalClicked.value = false
                    },
                color = Color.Black.copy(alpha = 0.5f),
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                ) {
                    GoalModal(distance = 5000, speed = 4.8, step = 3456) {
                        isGoalClicked.value = false
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewMainScreen() {
    val navController = rememberNavController()
    MainScreen(navController = navController)
}