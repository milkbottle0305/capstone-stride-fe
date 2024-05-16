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
val Gray300 = Color(0xffD7D7D7)
val Gray500 = Color(0xff999999)
val Gray700 = Color(0xff444444)
val White = Color(0xffffffff)
val Blue = Color(0xff007AFF)
val Pink = Color(0xffFFA8C7)
val Red = Color(0xffFF0000)

class StrideColors(
    primary: Color,
    secondary: Color,
    tertiary: Color,
    quaternary: Color,
    backgroundPrimary: Color,
    backgroundSecondary: Color,
    backgroundTertiary: Color,
    error: Color,
    hint: Color,
    textPrimary: Color,
    textSecondary: Color,
    textTertiary: Color,
    textQuaternary: Color,
    buttonPrimary: Color,
    buttonTextPrimary: Color,
    disableButtonPrimary: Color,
    disableButtonTextPrimary: Color,
    navigationBackground: Color,
    navigationSelected: Color,
    navigationUnselected: Color,
    isLight: Boolean,
) {
    var primary by mutableStateOf(primary)
        private set
    var secondary by mutableStateOf(secondary)
        private set
    var tertiary by mutableStateOf(tertiary)
        private set
    var quatrenary by mutableStateOf(quaternary)
        private set
    var backgroundPrimary by mutableStateOf(backgroundPrimary)
        private set
    var backgroundSecondary by mutableStateOf(backgroundSecondary)
        private set
    var backgroundTertiary by mutableStateOf(backgroundTertiary)
        private set
    var error by mutableStateOf(error)
        private set
    var hint by mutableStateOf(hint)
        private set
    var textPrimary by mutableStateOf(textPrimary)
        private set
    var textSecondary by mutableStateOf(textSecondary)
        private set
    var textTertiary by mutableStateOf(textTertiary)
        private set
    var textQuaternary by mutableStateOf(textQuaternary)
        private set
    var buttonPrimary by mutableStateOf(buttonPrimary)
        private set
    var buttonTextPrimary by mutableStateOf(buttonTextPrimary)
        private set
    var disableButtonPrimary by mutableStateOf(disableButtonPrimary)
        private set
    var disableButtonTextPrimary by mutableStateOf(disableButtonTextPrimary)
        private set
    var navigationBackground by mutableStateOf(navigationBackground)
        private set
    var navigationSelected by mutableStateOf(navigationSelected)
        private set
    var navigationUnselected by mutableStateOf(navigationUnselected)
        private set
    var isLight by mutableStateOf(isLight)
        private set


    fun copy(
        primary: Color = this.primary,
        secondary: Color = this.secondary,
        tertiary: Color = this.tertiary,
        backgroundPrimary: Color = this.backgroundPrimary,
        backgroundSecondary: Color = this.backgroundSecondary,
        backgroundTertiary: Color = this.backgroundTertiary,
        error: Color = this.error,
        hint: Color = this.hint,
        textPrimary: Color = this.textPrimary,
        textSecondary: Color = this.textSecondary,
        textTertiary: Color = this.textTertiary,
        textQuaternary: Color = this.textQuaternary,
        buttonPrimary: Color = this.buttonPrimary,
        buttonTextPrimary: Color = this.buttonTextPrimary,
        disableButtonPrimary: Color = this.disableButtonPrimary,
        disableButtonTextPrimary: Color = this.disableButtonTextPrimary,
        navigationBackground: Color = this.navigationBackground,
        navigationSelected: Color = this.navigationSelected,
        navigationUnselected: Color = this.navigationUnselected,
        isLight: Boolean = this.isLight,
    ) = StrideColors(
        primary = primary,
        secondary = secondary,
        tertiary = tertiary,
        quaternary = quatrenary,
        backgroundPrimary = backgroundPrimary,
        backgroundSecondary = backgroundSecondary,
        backgroundTertiary = backgroundTertiary,
        error = error,
        hint = hint,
        textPrimary = textPrimary,
        textSecondary = textSecondary,
        textTertiary = textTertiary,
        textQuaternary = textQuaternary,
        buttonPrimary = buttonPrimary,
        buttonTextPrimary = buttonTextPrimary,
        disableButtonPrimary = disableButtonPrimary,
        disableButtonTextPrimary = disableButtonTextPrimary,
        navigationBackground = navigationBackground,
        navigationSelected = navigationSelected,
        navigationUnselected = navigationUnselected,
        isLight = isLight,
    )

    fun updateColorsFrom(other: StrideColors) {
        primary = other.primary
        secondary = other.secondary
        tertiary = other.tertiary
        quatrenary = other.quatrenary
        backgroundPrimary = other.backgroundPrimary
        backgroundSecondary = other.backgroundSecondary
        backgroundTertiary = other.backgroundTertiary
        error = other.error
        hint = other.hint
        textPrimary = other.textPrimary
        textSecondary = other.textSecondary
        textTertiary = other.textTertiary
        textQuaternary = other.textQuaternary
        buttonPrimary = other.buttonPrimary
        buttonTextPrimary = other.buttonTextPrimary
        disableButtonPrimary = other.disableButtonPrimary
        disableButtonTextPrimary = other.disableButtonTextPrimary
        navigationBackground = other.navigationBackground
        navigationSelected = other.navigationSelected
        navigationUnselected = other.navigationUnselected
        isLight = other.isLight
    }
}

val LocalColors = staticCompositionLocalOf { lightColors() }