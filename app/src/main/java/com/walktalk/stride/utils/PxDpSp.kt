import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// dp(Dp) → px(Float)
@Composable
internal fun Dp.dpToPx(): Float {
    return this.value * LocalDensity.current.density
}

// dp(Dp) → sp(TextUnit)
@Composable
internal fun Dp.dpToSp(): TextUnit {
    return (this.value * LocalDensity.current.density / LocalDensity.current.fontScale).sp
}

// px(Float) → dp(Dp)
@Composable
internal fun Float.pxToDp(): Dp {
    return (this / LocalDensity.current.density).dp
}

// px(Float) → sp(TextUnit)
@Composable
internal fun Float.pxToSp(): TextUnit {
    return (this / LocalDensity.current.fontScale).sp
}

// sp(TextUnit) → dp(Dp)
@Composable
internal fun TextUnit.spToDp(): Dp {
    return (this.value * LocalDensity.current.fontScale / LocalDensity.current.density).dp
}

// sp(TextUnit) → px(Float)
@Composable
internal fun TextUnit.spToPx(): Float {
    return this.value * LocalDensity.current.fontScale
}