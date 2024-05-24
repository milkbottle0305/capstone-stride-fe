package com.walktalk.stride.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember


fun lightColors() = StrideColors(
    primary = Brown700,
    secondary = Brown100,
    tertiary = Blue,
    quaternary = Pink,
    backgroundPrimary = Brown100,
    backgroundSecondary = Brown500,
    backgroundTertiary = Brown700,
    error = Red,
    hint = Gray700,
    textPrimary = Black,
    textSecondary = Brown700,
    textTertiary = Brown100,
    textQuaternary = White,
    buttonPrimary = Brown700,
    buttonTextPrimary = White,
    disableButtonPrimary = Gray300,
    disableButtonTextPrimary = White,
    navigationBackground = White,
    navigationSelected = Brown700,
    navigationUnselected = Gray300,
    progressBackground = Gray300,
    progressIndicator = Brown700,
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
    hint = Gray700,
    textPrimary = White,
    textSecondary = Brown300,
    textTertiary = Brown500,
    textQuaternary = Black,
    buttonPrimary = Brown700,
    buttonTextPrimary = White,
    disableButtonPrimary = Gray300,
    disableButtonTextPrimary = White,
    navigationBackground = White,
    navigationSelected = Brown700,
    navigationUnselected = Gray700,
    progressBackground = Gray300,
    progressIndicator = Brown700,
    isLight = false
)

@Composable
fun StrideTheme(
    colors: StrideColors = StrideTheme.colors,
    darkColors: StrideColors = darkColors(),
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val currentColor = remember { if (darkTheme) darkColors else colors }
    val rememberedColors = remember { currentColor.copy() }.apply { updateColorsFrom(currentColor) }
    CompositionLocalProvider(
        LocalColors provides rememberedColors,
    ) {
        content()
    }
}

object StrideTheme {
    val colors: StrideColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current
}