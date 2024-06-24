@file:OptIn(ExperimentalMaterial3Api::class)

package com.walktalk.stride.presentation.main

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.walktalk.stride.R
import com.walktalk.stride.data.model.ApiState
import com.walktalk.stride.data.model.MyRoom
import com.walktalk.stride.data.model.RecentCourse
import com.walktalk.stride.data.model.TodayGoal
import com.walktalk.stride.presentation.components.StrideNavigationBar
import com.walktalk.stride.presentation.main.components.CourseMap
import com.walktalk.stride.presentation.main.components.GoalModal
import com.walktalk.stride.ui.theme.StrideTheme

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    LaunchedEffect(Unit) {
        viewModel.getTodayGoal()
        viewModel.getRecentCourses()
        viewModel.getMyRooms()
    }
    when (viewModel.allApiState.value) {
        is ApiState.Loading -> {
            // 로딩 중일 때 표시할 내용
            // CircularProgressIndicator()
        }

        is ApiState.Success -> {
            // 모든 API가 성공한 경우 실제 UI를 표시
            MainContent(
                navController = navController,
                size = viewModel.size.value,
                offsetX = viewModel.offsetX.value,
                offsetY = viewModel.offsetY.value,
                isGoalClicked = viewModel.isGoalClicked.value,
                isExerciseClicked = viewModel.isExerciseClicked.value,
                todayGoal = viewModel.todayGoal.value!!,
                recentCourse = viewModel.recentCourse.value,
                myRooms = viewModel.myRooms.value,
                setIsGoalClicked = viewModel::setIsGoalClicked,
                setIsExerciseClicked = viewModel::setIsExerciseClicked,
                setSize = viewModel::setSize,
                setOffsetX = viewModel::setOffsetX,
                setOffsetY = viewModel::setOffsetY,
            )
        }

        is ApiState.Error -> {
            // 에러 상태를 표시
            Text("Error loading data")
        }

        is ApiState.Empty -> {
            // 데이터가 없을 때 표시할 내용
            // Text("No data")
        }
    }
}

@Composable
fun MainContent(
    navController: NavController,
    size: Dp,
    offsetX: Dp,
    offsetY: Dp,
    isGoalClicked: Boolean,
    isExerciseClicked: Boolean,
    todayGoal: TodayGoal,
    recentCourse: RecentCourse?,
    myRooms: List<MyRoom>,
    setIsGoalClicked: (Boolean) -> Unit,
    setIsExerciseClicked: (Boolean) -> Unit,
    setSize: (Dp) -> Unit,
    setOffsetX: (Dp) -> Unit,
    setOffsetY: (Dp) -> Unit,
) {
    val density = LocalDensity.current
    val context = LocalContext.current
    val tierString = remember(todayGoal.tier) {
        when (todayGoal.tier) {
            0 -> {
                context.getString(R.string.tier_1)
            }

            1 -> context.getString(R.string.tier_2)
            2 -> context.getString(R.string.tier_3)
            3 -> context.getString(R.string.tier_4)
            else -> context.getString(R.string.tier_4)
        }
    }

    val nextTierString = remember(todayGoal.tier) {
        when (todayGoal.tier) {
            0 -> context.getString(R.string.tier_2)
            1 -> context.getString(R.string.tier_3)
            2 -> context.getString(R.string.tier_4)
            3 -> context.getString(R.string.tier_4)
            else -> context.getString(R.string.tier_4)
        }
    }

    LaunchedEffect(todayGoal.todayTierUp) {
        if (todayGoal.todayTierUp) {
            setIsGoalClicked(true)
        }
    }
    Scaffold(
        bottomBar = { StrideNavigationBar(navController = navController) },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = StrideTheme.colors.surface
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.mypage),
                        contentDescription = "mypage",
                        modifier = Modifier
                            .size(48.dp).align(Alignment.End)
                    )
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
                            .clickable { setIsGoalClicked(true) },
                        color = StrideTheme.colors.containerPrimary,
                        shape = RoundedCornerShape(16.dp),
                        shadowElevation = 4.dp
                    ) {
                        Column(
                            modifier = Modifier.padding(horizontal = 18.dp, vertical = 12.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = tierString,
                                    color = StrideTheme.colors.textSecondary,
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Image(
                                    painter = when (todayGoal.tier) {
                                        0 -> painterResource(id = R.drawable.tier_1)
                                        1 -> painterResource(id = R.drawable.tier_2)
                                        2 -> painterResource(id = R.drawable.tier_3)
                                        else -> painterResource(id = R.drawable.tier_4)
                                    },
                                    contentDescription = "tier_icon",
                                    modifier = Modifier
                                        .width(42.dp)
                                        .height(48.dp)
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                LinearProgressIndicator(
                                    progress = when (todayGoal.tier) {
                                        3 -> 1f
                                        else -> todayGoal.exp / 10f
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(24.dp)
                                        .clip(RoundedCornerShape(12.dp)),
                                    color = when (todayGoal.tier) {
                                        0 -> StrideTheme.colors.tier1
                                        1 -> StrideTheme.colors.tier2
                                        2 -> StrideTheme.colors.tier3
                                        else -> StrideTheme.colors.tier3
                                    },
                                    trackColor = StrideTheme.colors.progressBackground,
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "%,d".format(todayGoal.currentStride),
                                        color = StrideTheme.colors.textPrimary,
                                        fontSize = 25.sp,
                                        fontWeight = FontWeight.Bold,
                                    )
                                    Text(
                                        text = stringResource(
                                            R.string.stride_unit,
                                            todayGoal.goalStride
                                        ),
                                        color = StrideTheme.colors.textPrimary,
                                        fontSize = 12.sp,
                                    )
                                }
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "%.1f".format(todayGoal.currentSpeed),
                                        color = StrideTheme.colors.textPrimary,
                                        fontSize = 25.sp,
                                        fontWeight = FontWeight.Bold,
                                    )
                                    Text(
                                        text = stringResource(
                                            R.string.speed_unit,
                                            todayGoal.goalSpeed
                                        ),
                                        color = StrideTheme.colors.textPrimary,
                                        fontSize = 12.sp,
                                    )
                                }
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "%,d".format(todayGoal.currentStep),
                                        color = StrideTheme.colors.textPrimary,
                                        fontSize = 25.sp,
                                        fontWeight = FontWeight.Bold,
                                    )
                                    Text(
                                        text = stringResource(
                                            R.string.step_unit,
                                            todayGoal.goalStep
                                        ),
                                        color = StrideTheme.colors.textPrimary,
                                        fontSize = 12.sp,
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = stringResource(id = R.string.main_join_room),
                        color = StrideTheme.colors.textSecondary,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    if (myRooms.isEmpty()) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(id = R.string.empty),
                            color = StrideTheme.colors.textPrimary,
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    } else {
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 10.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                        ) {
                            items(myRooms.size) {
                                Surface(
                                    color = StrideTheme.colors.containerPrimary,
                                    shape = RoundedCornerShape(10.dp),
                                    shadowElevation = 4.dp,
                                ) {
                                    Row(
                                        modifier = Modifier.padding(8.dp)
                                    ) {
                                        CourseMap(
                                            modifier = Modifier
                                                .height(size)
                                                .width(size)
                                                .clip(RoundedCornerShape(10.dp)),
                                            course = myRooms[it].course
                                        )
                                        Spacer(modifier = Modifier.width(12.dp))
                                        Column(modifier = Modifier
                                            .width(200.dp)
                                            .onSizeChanged {
                                                with(density) {
                                                    setSize(it.height.toDp())
                                                }
                                            }
                                        ) {
                                            Text(
                                                text = myRooms[it].roomName,
                                                color = StrideTheme.colors.textSecondary,
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold,
                                                maxLines = 1,
                                            )
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                text = myRooms[it].courseName,
                                                color = StrideTheme.colors.textSecondary,
                                                fontSize = 16.sp,
                                                maxLines = 1,
                                            )
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                text = stringResource(
                                                    R.string.together_participating,
                                                    myRooms[it].participatingCount
                                                ),
                                                color = StrideTheme.colors.textSecondary,
                                                fontSize = 16.sp,
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = stringResource(id = R.string.main_current_course),
                        color = StrideTheme.colors.textSecondary,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    if (recentCourse == null) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(id = R.string.empty),
                            color = StrideTheme.colors.textPrimary,
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    } else {
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth(),
                            color = StrideTheme.colors.containerPrimary,
                            shape = RoundedCornerShape(16.dp),
                            shadowElevation = 4.dp
                        ) {
                            Row(
                                modifier = Modifier.padding(8.dp)
                            ) {
                                CourseMap(
                                    modifier = Modifier
                                        .width(size)
                                        .height(size),
                                    course = recentCourse.course
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = recentCourse.courseName,
                                        color = StrideTheme.colors.textSecondary,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        maxLines = 1,
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = stringResource(
                                            R.string.main_course_distance,
                                            recentCourse.distance
                                        ),
                                        color = StrideTheme.colors.textSecondary,
                                        fontSize = 16.sp,
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = stringResource(
                                            R.string.main_course_duration,
                                            recentCourse.time
                                        ),
                                        color = StrideTheme.colors.textSecondary,
                                        fontSize = 16.sp,
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
            if (isExerciseClicked) {
                // 클릭 상태일 때, 새로운 버튼 생성
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }) {
                            setIsExerciseClicked(false)
                        },
                    color = Color.Black.copy(alpha = 0.5f),
                ) {}
                Surface(
                    modifier = Modifier
                        .size(100.dp)
                        .offset(x = offsetX, y = offsetY + (-200).dp)
                        .clickable { navController.navigate("exercise?exerciseType=default&goalStep=${todayGoal.goalStep - todayGoal.currentStep}") },
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
                        .offset(x = offsetX + (-130).dp, y = offsetY + (-100).dp)
                        .clickable { navController.navigate("exercise?exerciseType=speed&goalStep=${todayGoal.goalStep - todayGoal.currentStep}") },
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
                        .offset(x = offsetX + 130.dp, y = offsetY + (-100).dp)
                        .clickable { navController.navigate("exercise?exerciseType=stride&goalStep=${todayGoal.goalStep - todayGoal.currentStep}") },
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
                        .clickable { setIsExerciseClicked(!isExerciseClicked) }
                        .onGloballyPositioned { coordinates ->
                            val position = coordinates.positionInWindow()
                            with(density) {
                                setOffsetX(position.x.toDp())
                                setOffsetY(position.y.toDp())
                            }
                            Log.d("offset", "offsetX: ${offsetX.value}, offsetY: ${offsetY.value}")
                        },
                    shape = CircleShape,
                    color = StrideTheme.colors.buttonPrimary,
                ) {
                    Text(
                        modifier = Modifier
                            .wrapContentSize(Alignment.Center),
                        text = if (isExerciseClicked) stringResource(id = R.string.main_exercise_cancel) else stringResource(
                            id = R.string.main_exercise
                        ),
                        color = StrideTheme.colors.buttonTextPrimary,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }
            if (isGoalClicked) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }) {
                            setIsGoalClicked(false)
                        },
                    color = Color.Black.copy(alpha = 0.5f),
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                    ) {
                        GoalModal(
                            allComplete = todayGoal.todayTierUp,
                            levelString = tierString,
                            nextLevelString = nextTierString,
                            stride = todayGoal.goalStride,
                            speed = todayGoal.goalSpeed,
                            step = todayGoal.goalStep
                        ) {
                            setIsGoalClicked(false)
                        }
                    }
                }
            }
        }
    }
}