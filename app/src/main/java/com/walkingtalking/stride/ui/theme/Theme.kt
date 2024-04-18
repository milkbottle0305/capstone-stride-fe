package com.walkingtalking.stride.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext


val Brown100 = Color(0xffFFFCF5)
val Brown300 = Color(0xffFFF6E5)
val Brown500 = Color(0xffFFF1D4)
val Brown700 = Color(0xff460000)
val Orange = Color(0xffFF9800)
val Black = Color(0xff000000)
val White = Color(0xffffffff)
val Blue = Color(0xff007AFF)
val Pink = Color(0xffFFA8C7)
val Red = Color(0xffFF0000)

fun lightColors() = StrideColors(
    primary = Brown700,
    secondary = Brown100,
    tertiary = Blue,
    quaternary = Pink,
    backgroundPrimary = Brown500,
    backgroundSecondary = Brown100,
    backgroundTertiary = Brown500,
    error = Red,
    textPrimary = Black,
    textSecondary = Brown700,
    textTertiary = Brown100,
    textQuaternary = White,
    isLight = true
)

fun darkColors() = StrideColors(
    primary = Brown300,
    secondary = Brown500,
    tertiary = Blue,
    quaternary = Pink,
    backgroundPrimary = Brown700,
    backgroundSecondary = Brown300,
    backgroundTertiary = Brown500,
    error = Red,
    textPrimary = White,
    textSecondary = Brown300,
    textTertiary = Brown500,
    textQuaternary = Black,
    isLight = false
)

@Composable
fun StrideTheme(
    colors: StrideColors = StrideTheme.colors,
    darkColors: StrideColors = darkColors(),
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val currentColor = remember { if (darkColors != null && darkTheme) darkColors else colors }
    val rememberedColors = remember { currentColor.copy() }.apply { updateColorsFrom(currentColor) }
    CompositionLocalProvider(
        LocalColors provides rememberedColors,
    ) {
    }
}

object StrideTheme {
    val colors: StrideColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current
}