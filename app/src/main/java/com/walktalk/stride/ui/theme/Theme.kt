package com.walktalk.stride.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember

fun lightColors() = StrideColors(
    surface = Brown100,
    buttonPrimary = Brown700,
    buttonTextPrimary = White,
    buttonBorderPrimary = Brown700,
    buttonDisabledPrimary = Gray300,
    buttonDisabledTextPrimary = White,
    buttonDisabledBorderPrimary = Gray300,
    buttonSecondary = Brown100,
    buttonTextSecondary = Brown700,
    buttonBorderSecondary = Brown700,
    buttonDisabledSecondary = Gray300,
    buttonDisabledTextSecondary = White,
    buttonDisabledBorderSecondary = Gray300,
    containerPrimary = Brown300,
    containerTextPrimary = Black,
    containerBorderPrimary = Brown300,
    containerSecondary = Brown700,
    containerTextSecondary = White,
    containerBorderSecondary = Brown700,
    exerciseContainerSelected = Brown700,
    exerciseContainerTextPrimarySelected = Brown500,
    exerciseContainerUnselected = Brown500,
    exerciseContainerTextPrimaryUnselected = Black,
    exerciseContainerTextSecondaryUnselected = Brown700,
    textPrimary = Black,
    textSecondary = Brown700,
    textTertiary = White,
    textFieldPrimary = Brown500,
    textFieldTextPrimary = Black,
    textFieldBorderPrimary = Brown500,
    textFieldSecondary = White,
    textFieldTextSecondary = Black,
    textFieldBorderSecondary = Gray300,
    textFieldHint = Gray500,
    navBackground = White,
    navSelected = Brown700,
    navUnselected = Gray500,
    level1 = LevelRed,
    level2 = LevelOrange,
    level3 = LevelBlue,
    levelBackground = Gray300,
    modalBackground = ModalBlack,
    progress = Brown700,
    progressBackground = Gray300,
    error = Red,
    isLight = true
)

fun darkColors() = lightColors()

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