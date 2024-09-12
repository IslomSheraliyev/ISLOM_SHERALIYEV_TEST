package uz.isheraliyev.project.ui.design.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import uz.isheraliyev.project.ui.design.color.blueDark
import uz.isheraliyev.project.ui.design.color.blueLight
import uz.isheraliyev.project.ui.design.color.projectDarkColorScheme
import uz.isheraliyev.project.ui.design.color.projectLightColorScheme
import uz.isheraliyev.project.ui.design.font.localFont

private val lightColorScheme = projectLightColorScheme()
private val darkColorScheme = projectDarkColorScheme()

private val colorPalette = lightColorScheme(
    primary = blueLight,
    secondary = Color.Blue,
    tertiary = Color.Blue,
    onError = Color.Magenta,
)

private val darkColorPalette = darkColorScheme(
    primary = blueDark,
    secondary = Color.Blue,
    tertiary = Color.Blue,
    onError = Color.Magenta,
)

@Composable
fun ProjectTheme(
    isSystemInDark: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {

    val localColorScheme = if (isSystemInDark) darkColorScheme else lightColorScheme

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (isSystemInDark) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        isSystemInDark -> darkColorPalette
        else -> colorPalette
    }

    YesTMSTheme(
        colorScheme = localColorScheme,
        typography = localFont
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content
        )
    }
}