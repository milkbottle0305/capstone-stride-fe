package com.walktalk.stride.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Brown100 = Color(0xffFFFCF5)
val Brown300 = Color(0xffFFF6E5)
val Brown500 = Color(0xffFFF1D4)
val Brown700 = Color(0xff460000)
val Orange = Color(0xffFF9800)
val Black = Color(0xff000000)
val ModalBlack = Color(0Xff000000).copy(alpha = 0.5f)
val Gray300 = Color(0xffD7D7D7)
val Gray500 = Color(0xff999999)
val Gray700 = Color(0xff444444)
val White = Color(0xffffffff)
val Blue = Color(0xff007AFF)
val Pink = Color(0xffFFA8C7)
val Red = Color(0xffFF0000)
val LevelRed = Color(0xffE34A33)
val LevelOrange = Color(0xffF58220)
val LevelBlue = Color(0xff2BA3DE)

class StrideColors(
    surface: Color,
    buttonPrimary: Color,
    buttonTextPrimary: Color,
    buttonBorderPrimary: Color,
    buttonDisabledPrimary: Color,
    buttonDisabledTextPrimary: Color,
    buttonDisabledBorderPrimary: Color,
    buttonSecondary: Color,
    buttonTextSecondary: Color,
    buttonBorderSecondary: Color,
    buttonDisabledSecondary: Color,
    buttonDisabledTextSecondary: Color,
    buttonDisabledBorderSecondary: Color,
    containerPrimary: Color,
    containerTextPrimary: Color,
    containerBorderPrimary: Color,
    containerSecondary: Color,
    containerTextSecondary: Color,
    containerBorderSecondary: Color,
    exerciseContainerSelected: Color,
    exerciseContainerTextPrimarySelected: Color,
    exerciseContainerUnselected: Color,
    exerciseContainerTextPrimaryUnselected: Color,
    exerciseContainerTextSecondaryUnselected: Color,
    textPrimary: Color,
    textSecondary: Color,
    textTertiary: Color,
    textFieldPrimary: Color,
    textFieldTextPrimary: Color,
    textFieldBorderPrimary: Color,
    textFieldSecondary: Color,
    textFieldTextSecondary: Color,
    textFieldBorderSecondary: Color,
    textFieldHint: Color,
    navBackground: Color,
    navSelected: Color,
    navUnselected: Color,
    level1: Color,
    level2: Color,
    level3: Color,
    levelBackground: Color,
    modalBackground: Color,
    progress: Color,
    progressBackground: Color,
    error: Color,
    isLight: Boolean
) {
    var surface by mutableStateOf(surface)
        private set
    var buttonPrimary by mutableStateOf(buttonPrimary)
        private set
    var buttonTextPrimary by mutableStateOf(buttonTextPrimary)
        private set
    var buttonBorderPrimary by mutableStateOf(buttonBorderPrimary)
        private set
    var buttonDisabledPrimary by mutableStateOf(buttonDisabledPrimary)
        private set
    var buttonDisabledTextPrimary by mutableStateOf(buttonDisabledTextPrimary)
        private set
    var buttonDisabledBorderPrimary by mutableStateOf(buttonDisabledBorderPrimary)
        private set
    var buttonSecondary by mutableStateOf(buttonSecondary)
        private set
    var buttonTextSecondary by mutableStateOf(buttonTextSecondary)
        private set
    var buttonBorderSecondary by mutableStateOf(buttonBorderSecondary)
        private set
    var buttonDisabledSecondary by mutableStateOf(buttonDisabledSecondary)
        private set
    var buttonDisabledTextSecondary by mutableStateOf(buttonDisabledTextSecondary)
        private set
    var buttonDisabledBorderSecondary by mutableStateOf(buttonDisabledBorderSecondary)
        private set
    var containerPrimary by mutableStateOf(containerPrimary)
        private set
    var containerTextPrimary by mutableStateOf(containerTextPrimary)
        private set
    var containerBorderPrimary by mutableStateOf(containerBorderPrimary)
        private set
    var containerSecondary by mutableStateOf(containerSecondary)
        private set
    var containerTextSecondary by mutableStateOf(containerTextSecondary)
        private set
    var containerBorderSecondary by mutableStateOf(containerBorderSecondary)
        private set
    var exerciseContainerSelected by mutableStateOf(exerciseContainerSelected)
        private set
    var exerciseContainerTextPrimarySelected by mutableStateOf(exerciseContainerTextPrimarySelected)
        private set
    var exerciseContainerUnselected by mutableStateOf(exerciseContainerUnselected)
        private set
    var exerciseContainerTextPrimaryUnselected by mutableStateOf(
        exerciseContainerTextPrimaryUnselected
    )
        private set
    var exerciseContainerTextSecondaryUnselected by mutableStateOf(
        exerciseContainerTextSecondaryUnselected
    )
        private set
    var textPrimary by mutableStateOf(textPrimary)
        private set
    var textSecondary by mutableStateOf(textSecondary)
        private set
    var textTertiary by mutableStateOf(textTertiary)
        private set
    var textFieldPrimary by mutableStateOf(textFieldPrimary)
        private set
    var textFieldTextPrimary by mutableStateOf(textFieldTextPrimary)
        private set
    var textFieldBorderPrimary by mutableStateOf(textFieldBorderPrimary)
        private set
    var textFieldSecondary by mutableStateOf(textFieldSecondary)
        private set
    var textFieldTextSecondary by mutableStateOf(textFieldTextSecondary)
        private set
    var textFieldBorderSecondary by mutableStateOf(textFieldBorderSecondary)
        private set
    var textFieldHint by mutableStateOf(textFieldHint)
        private set
    var navBackground by mutableStateOf(navBackground)
        private set
    var navSelected by mutableStateOf(navSelected)
        private set
    var navUnselected by mutableStateOf(navUnselected)
        private set
    var level1 by mutableStateOf(level1)
        private set
    var level2 by mutableStateOf(level2)
        private set
    var level3 by mutableStateOf(level3)
        private set
    var levelBackground by mutableStateOf(levelBackground)
        private set
    var modalBackground by mutableStateOf(modalBackground)
        private set
    var progress by mutableStateOf(progress)
        private set
    var progressBackground by mutableStateOf(progressBackground)
        private set
    var error by mutableStateOf(error)
        private set
    var isLight by mutableStateOf(isLight)
        private set


    fun copy(
        surface: Color = this.surface,
        buttonPrimary: Color = this.buttonPrimary,
        buttonTextPrimary: Color = this.buttonTextPrimary,
        buttonBorderPrimary: Color = this.buttonBorderPrimary,
        buttonDisabledPrimary: Color = this.buttonDisabledPrimary,
        buttonDisabledTextPrimary: Color = this.buttonDisabledTextPrimary,
        buttonDisabledBorderPrimary: Color = this.buttonDisabledBorderPrimary,
        buttonSecondary: Color = this.buttonSecondary,
        buttonTextSecondary: Color = this.buttonTextSecondary,
        buttonBorderSecondary: Color = this.buttonBorderSecondary,
        buttonDisabledSecondary: Color = this.buttonDisabledSecondary,
        buttonDisabledTextSecondary: Color = this.buttonDisabledTextSecondary,
        buttonDisabledBorderSecondary: Color = this.buttonDisabledBorderSecondary,
        containerPrimary: Color = this.containerPrimary,
        containerTextPrimary: Color = this.containerTextPrimary,
        containerBorderPrimary: Color = this.containerBorderPrimary,
        containerSecondary: Color = this.containerSecondary,
        containerTextSecondary: Color = this.containerTextSecondary,
        containerBorderSecondary: Color = this.containerBorderSecondary,
        exerciseContainerSelected: Color = this.exerciseContainerSelected,
        exerciseContainerTextPrimarySelected: Color = this.exerciseContainerTextPrimarySelected,
        exerciseContainerUnselected: Color = this.exerciseContainerUnselected,
        exerciseContainerTextPrimaryUnselected: Color = this.exerciseContainerTextPrimaryUnselected,
        exerciseContainerTextSecondaryUnselected: Color = this.exerciseContainerTextSecondaryUnselected,
        textPrimary: Color = this.textPrimary,
        textSecondary: Color = this.textSecondary,
        textTertiary: Color = this.textTertiary,
        textFieldPrimary: Color = this.textFieldPrimary,
        textFieldTextPrimary: Color = this.textFieldTextPrimary,
        textFieldBorderPrimary: Color = this.textFieldBorderPrimary,
        textFieldSecondary: Color = this.textFieldSecondary,
        textFieldTextSecondary: Color = this.textFieldTextSecondary,
        textFieldBorderSecondary: Color = this.textFieldBorderSecondary,
        textFieldHint: Color = this.textFieldHint,
        navBackground: Color = this.navBackground,
        navSelected: Color = this.navSelected,
        navUnselected: Color = this.navUnselected,
        level1: Color = this.level1,
        level2: Color = this.level2,
        level3: Color = this.level3,
        levelBackground: Color = this.levelBackground,
        modalBackground: Color = this.modalBackground,
        progress: Color = this.progress,
        progressBackground: Color = this.progressBackground,
        error: Color = this.error,
        isLight: Boolean = this.isLight
    ) = StrideColors(
        surface = surface,
        buttonPrimary,
        buttonTextPrimary,
        buttonBorderPrimary,
        buttonDisabledPrimary,
        buttonDisabledTextPrimary,
        buttonDisabledBorderPrimary,
        buttonSecondary,
        buttonTextSecondary,
        buttonBorderSecondary,
        buttonDisabledSecondary,
        buttonDisabledTextSecondary,
        buttonDisabledBorderSecondary,
        containerPrimary,
        containerTextPrimary,
        containerBorderPrimary,
        containerSecondary,
        containerTextSecondary,
        containerBorderSecondary,
        exerciseContainerSelected,
        exerciseContainerTextPrimarySelected,
        exerciseContainerUnselected,
        exerciseContainerTextPrimaryUnselected,
        exerciseContainerTextSecondaryUnselected,
        textPrimary,
        textSecondary,
        textTertiary,
        textFieldPrimary,
        textFieldTextPrimary,
        textFieldBorderPrimary,
        textFieldSecondary,
        textFieldTextSecondary,
        textFieldBorderSecondary,
        textFieldHint,
        navBackground,
        navSelected,
        navUnselected,
        level1,
        level2,
        level3,
        levelBackground,
        modalBackground,
        progress,
        progressBackground,
        error,
        isLight
    )

    fun updateColorsFrom(other: StrideColors) {
        surface = other.surface
        buttonPrimary = other.buttonPrimary
        buttonTextPrimary = other.buttonTextPrimary
        buttonBorderPrimary = other.buttonBorderPrimary
        buttonDisabledPrimary = other.buttonDisabledPrimary
        buttonDisabledTextPrimary = other.buttonDisabledTextPrimary
        buttonDisabledBorderPrimary = other.buttonDisabledBorderPrimary
        buttonSecondary = other.buttonSecondary
        buttonTextSecondary = other.buttonTextSecondary
        buttonBorderSecondary = other.buttonBorderSecondary
        buttonDisabledSecondary = other.buttonDisabledSecondary
        buttonDisabledTextSecondary = other.buttonDisabledTextSecondary
        buttonDisabledBorderSecondary = other.buttonDisabledBorderSecondary
        containerPrimary = other.containerPrimary
        containerTextPrimary = other.containerTextPrimary
        containerBorderPrimary = other.containerBorderPrimary
        containerSecondary = other.containerSecondary
        containerTextSecondary = other.containerTextSecondary
        containerBorderSecondary = other.containerBorderSecondary
        exerciseContainerSelected = other.exerciseContainerSelected
        exerciseContainerTextPrimarySelected = other.exerciseContainerTextPrimarySelected
        exerciseContainerUnselected = other.exerciseContainerUnselected
        exerciseContainerTextPrimaryUnselected = other.exerciseContainerTextPrimaryUnselected
        exerciseContainerTextSecondaryUnselected = other.exerciseContainerTextSecondaryUnselected
        textPrimary = other.textPrimary
        textSecondary = other.textSecondary
        textTertiary = other.textTertiary
        textFieldPrimary = other.textFieldPrimary
        textFieldTextPrimary = other.textFieldTextPrimary
        textFieldBorderPrimary = other.textFieldBorderPrimary
        textFieldSecondary = other.textFieldSecondary
        textFieldTextSecondary = other.textFieldTextSecondary
        textFieldBorderSecondary = other.textFieldBorderSecondary
        textFieldHint = other.textFieldHint
        navBackground = other.navBackground
        navSelected = other.navSelected
        navUnselected = other.navUnselected
        level1 = other.level1
        level2 = other.level2
        level3 = other.level3
        levelBackground = other.levelBackground
        modalBackground = other.modalBackground
        progress = other.progress
        progressBackground = other.progressBackground
        error = other.error
        isLight = other.isLight
    }
}

val LocalColors = staticCompositionLocalOf { lightColors() }