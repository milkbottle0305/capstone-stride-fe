package com.walktalk.stride.presentation.exercise.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.walktalk.stride.R
import com.walktalk.stride.ui.theme.StrideTheme

@Composable
fun ShareModal(
    inputText: String,
    enabled: Boolean,
    onTextChange: (String) -> Unit,
    onCancelClick: () -> Unit,
    onInputSubmit: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = StrideTheme.colors.textPrimary.copy(alpha = 0.5f))
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = { onCancelClick() }
            ),
        contentAlignment = Alignment.Center,
    ) {
        Surface(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .clip(RoundedCornerShape(10.dp)),
            shape = RoundedCornerShape(10.dp),
            color = StrideTheme.colors.backgroundPrimary
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        stringResource(id = R.string.exercise_course_name_input),
                        fontSize = 25.sp,
                        color = StrideTheme.colors.textPrimary,
                        fontWeight = FontWeight.Bold
                    )
                    Image(
                        painter = painterResource(id = R.drawable.cancel),
                        modifier = Modifier
                            .height(24.dp)
                            .width(24.dp)
                            .clickable { onCancelClick() },
                        contentDescription = "Cancel Button"
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(StrideTheme.colors.buttonTextPrimary)
                        .border(
                            1.dp,
                            StrideTheme.colors.disableButtonPrimary,
                            RoundedCornerShape(10.dp)
                        )
                        .height(40.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    value = inputText,
                    onValueChange = { if (it.length <= 20) onTextChange(it) },
                    singleLine = true,
                    textStyle = TextStyle(
                        fontSize = 24.sp,
                        color = StrideTheme.colors.textPrimary,
                    ),
                    decorationBox = { innerTextField ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            innerTextField()
                        }
                    },
                )
                Spacer(modifier = Modifier.height(24.dp))
                Surface(
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .clickable { if (enabled) onInputSubmit() },
                    shape = RoundedCornerShape(5.dp),
                    color = if (enabled) StrideTheme.colors.primary else StrideTheme.colors.disableButtonPrimary
                ) {
                    Text(
                        stringResource(id = R.string.exercise_course_name_submit),
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 3.dp),
                        fontSize = 24.sp,
                        color = StrideTheme.colors.textQuaternary,
                    )
                }
            }
        }
    }
}