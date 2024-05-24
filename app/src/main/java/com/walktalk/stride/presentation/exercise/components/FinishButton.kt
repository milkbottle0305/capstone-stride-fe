package com.walktalk.stride.presentation.exercise.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.walktalk.stride.R
import com.walktalk.stride.ui.theme.StrideTheme

@Composable
fun CompleteButton(modifier: Modifier, enabled: Boolean, onClick: () -> Unit) {
    val context = LocalContext.current
    Surface(
        modifier = modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .clickable {
                if (enabled)
                    onClick()
            },
        color = if (enabled) StrideTheme.colors.buttonPrimary else StrideTheme.colors.disableButtonPrimary
    ) {
        Text(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 19.dp),
            text = stringResource(id = R.string.exercise_stop),
            color = StrideTheme.colors.textTertiary,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
    }
}