package com.walktalk.stride.presentation.exercise.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.walktalk.stride.R
import com.walktalk.stride.ui.theme.StrideTheme

@Composable
fun ShareButtonRow(onNoClick: () -> Unit, onYesClick: () -> Unit) {
    Row(
        modifier = Modifier.padding(horizontal = 30.dp)
    ) {
        Surface(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .weight(1f)
                .border(width = 1.dp, color = StrideTheme.colors.buttonBorderSecondary, shape = RoundedCornerShape(10.dp))
                .clickable(onClick = onNoClick),
            color = StrideTheme.colors.buttonSecondary,
            shape = RoundedCornerShape(10.dp),
        ) {
            Text(
                stringResource(id = R.string.no),
                textAlign = TextAlign.Center,
                color = StrideTheme.colors.buttonTextSecondary,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.width(20.dp))
        Surface(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .weight(1f)
                .border(width = 1.dp, color = StrideTheme.colors.buttonBorderPrimary, shape = RoundedCornerShape(10.dp))
                .clickable(onClick = onYesClick),
            color = StrideTheme.colors.buttonPrimary,
            shape = RoundedCornerShape(10.dp),
        ) {
            Text(
                stringResource(id = R.string.yes),
                textAlign = TextAlign.Center,
                color = StrideTheme.colors.buttonTextPrimary,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
fun ShareButtonRowPreview() {
    StrideTheme {
        ShareButtonRow({}, {})
    }
}