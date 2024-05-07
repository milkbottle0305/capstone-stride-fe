package com.walkingtalking.stride.presentation.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.walkingtalking.stride.R
import com.walkingtalking.stride.ui.theme.StrideTheme

@Composable
fun GoalModal(distance: Int, speed: Double, step: Int, onClick: () -> Unit) {
    Surface(
        color = StrideTheme.colors.backgroundPrimary,
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.End)
                    .clickable { onClick() },
                painter = painterResource(id = R.drawable.cancel),
                contentDescription = "modal_close",
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(id = R.string.goal_signup_congratulations),
                fontSize = 30.sp,
                color = StrideTheme.colors.textPrimary
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(id = R.string.goal_today),
                fontSize = 20.sp,
                color = StrideTheme.colors.textPrimary
            )
            Spacer(modifier = Modifier.height(23.dp))
            Text(
                text = stringResource(id = R.string.goal_present),
                fontSize = 30.sp,
                color = StrideTheme.colors.textPrimary
            )
            Spacer(modifier = Modifier.height(36.dp))
            Row {
                Text(
                    text = stringResource(id = R.string.distance),
                    fontSize = 20.sp,
                    color = StrideTheme.colors.textPrimary
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = stringResource(id = R.string.distance_unit, distance),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = StrideTheme.colors.textSecondary
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Text(
                    text = stringResource(id = R.string.speed),
                    fontSize = 20.sp,
                    color = StrideTheme.colors.textPrimary
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = stringResource(id = R.string.speed_unit, speed),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = StrideTheme.colors.textSecondary
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Text(
                    text = stringResource(id = R.string.step),
                    fontSize = 20.sp,
                    color = StrideTheme.colors.textPrimary
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = stringResource(id = R.string.step_unit, step),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = StrideTheme.colors.textSecondary
                )
            }
        }
    }
}

@Preview
@Composable
fun GoalModalPreview() {
    StrideTheme {
        GoalModal(distance = 5000, speed = 4.8, step = 4000) {}
    }
}