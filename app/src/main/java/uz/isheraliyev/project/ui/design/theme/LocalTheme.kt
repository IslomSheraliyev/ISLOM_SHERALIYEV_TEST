package uz.isheraliyev.project.ui.design.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import uz.isheraliyev.project.ui.design.color.LocalColor
import uz.isheraliyev.project.ui.design.color.LocalCustomColorScheme
import uz.isheraliyev.project.ui.design.font.LocalCustomFont
import uz.isheraliyev.project.ui.design.font.LocalFont

object LocalTheme {
    val color: LocalColor
        @Composable
        @ReadOnlyComposable
        get() = LocalCustomColorScheme.current

    val font: LocalFont
        @Composable
        @ReadOnlyComposable
        get() = LocalCustomFont.current
}

@Composable
fun YesTMSTheme(
    colorScheme: LocalColor,
    typography: LocalFont,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalCustomColorScheme provides colorScheme,
        LocalCustomFont provides typography
    ) {
        content()
    }
}