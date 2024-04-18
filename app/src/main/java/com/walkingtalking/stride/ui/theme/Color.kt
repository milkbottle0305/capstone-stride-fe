package com.walkingtalking.stride.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


class StrideColors(
    primary: Color,
    secondary: Color,
    tertiary: Color,
    quaternary: Color,
    backgroundPrimary: Color,
    backgroundSecondary: Color,
    backgroundTertiary: Color,
    error: Color,
    textPrimary: Color,
    textSecondary: Color,
    textTertiary: Color,
    textQuaternary: Color,
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
    var textPrimary by mutableStateOf(textPrimary)
        private set
    var textSecondary by mutableStateOf(textSecondary)
        private set
    var textTertiary by mutableStateOf(textTertiary)
        private set
    var textQuaternary by mutableStateOf(textQuaternary)
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
        textPrimary: Color = this.textPrimary,
        textSecondary: Color = this.textSecondary,
        textTertiary: Color = this.textTertiary,
        textQuaternary: Color = this.textQuaternary,
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
        textPrimary = textPrimary,
        textSecondary = textSecondary,
        textTertiary = textTertiary,
        textQuaternary = textQuaternary,
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
        textPrimary = other.textPrimary
        textSecondary = other.textSecondary
        textTertiary = other.textTertiary
        textQuaternary = other.textQuaternary
        isLight = other.isLight
    }
}

val LocalColors = staticCompositionLocalOf { lightColors() }