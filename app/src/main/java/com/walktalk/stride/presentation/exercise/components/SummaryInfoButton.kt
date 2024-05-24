package com.walktalk.stride.presentation.exercise.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.walktalk.stride.ui.theme.StrideTheme

@Composable
fun SummaryInfoButton(
    modifier: Modifier,
    title: String,
    description: String,
    isClicked: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .padding(vertical = 20.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable { onClick() },
        color = if (isClicked) StrideTheme.colors.backgroundTertiary else StrideTheme.colors.backgroundSecondary
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                title,
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
                color = if (isClicked) StrideTheme.colors.backgroundSecondary else StrideTheme.colors.textPrimary

            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                description,
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = if (isClicked) StrideTheme.colors.backgroundSecondary else StrideTheme.colors.textSecondary
            )
        }
    }
}