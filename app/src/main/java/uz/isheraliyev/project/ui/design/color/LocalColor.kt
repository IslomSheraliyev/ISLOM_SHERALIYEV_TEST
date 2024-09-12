package uz.isheraliyev.project.ui.design.color

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.graphics.Color

class LocalColor(
    cardColor: Color,
    cardBorderColor: Color,
    containerColor: Color,
    selectedItemColor: Color,
    menuColor: Color,
    dividerColor: Color,
    blue: Color,
    label: Color,
    grey: Color,
    secondary: Color,
    textPrimary: Color,
) {

    val cardColor by mutableStateOf(cardColor, structuralEqualityPolicy())
    val cardBorder by mutableStateOf(cardBorderColor, structuralEqualityPolicy())
    val containerColor by mutableStateOf(containerColor, structuralEqualityPolicy())
    val selectedItemColor by mutableStateOf(selectedItemColor, structuralEqualityPolicy())
    val menuColor by mutableStateOf(menuColor, structuralEqualityPolicy())
    val dividerColor by mutableStateOf(dividerColor, structuralEqualityPolicy())

    val blue by mutableStateOf(blue, structuralEqualityPolicy())
    val label by mutableStateOf(label, structuralEqualityPolicy())
    val grey by mutableStateOf(grey, structuralEqualityPolicy())
    val secondary by mutableStateOf(secondary, structuralEqualityPolicy())
    val textPrimary by mutableStateOf(textPrimary, structuralEqualityPolicy())
}

fun projectLightColorScheme(
    cardColor: Color = cardColorLight,
    cardBorder: Color = cardBorderLight,
    containerColor: Color = containerColorLight,
    selectedItemColor: Color = selectedMenuItemColorLight,
    menuColor: Color = menuColorLight,
    dividerColor: Color = dividerColorLight,
    blue: Color = blueLight,
    label: Color = labelLight,
    grey: Color = greyLight,
    secondary: Color = secondaryLight,
    textPrimary: Color = textPrimaryLight,
) = LocalColor(
    cardColor = cardColor,
    cardBorderColor = cardBorder,
    containerColor = containerColor,
    selectedItemColor = selectedItemColor,
    menuColor = menuColor,
    dividerColor = dividerColor,
    blue = blue,
    label = label,
    grey = grey,
    secondary = secondary,
    textPrimary = textPrimary
)

fun projectDarkColorScheme(
    cardColor: Color = cardColorDark,
    cardBorder: Color = cardBorderDark,
    containerColor: Color = containerColorDark,
    selectedItemColor: Color = selectedMenuItemColorDark,
    menuColor: Color = menuColorDark,
    dividerColor: Color = dividerColorDark,
    blue: Color = blueDark,
    label: Color = labelDark,
    grey: Color = greyDark,
    secondary: Color = secondaryDark,
    textPrimary: Color = textPrimaryDark,
) = LocalColor(
    cardColor = cardColor,
    cardBorderColor = cardBorder,
    containerColor = containerColor,
    selectedItemColor = selectedItemColor,
    menuColor = menuColor,
    dividerColor = dividerColor,
    blue = blue,
    label = label,
    grey = grey,
    secondary = secondary,
    textPrimary = textPrimary
)

val LocalCustomColorScheme = staticCompositionLocalOf { projectLightColorScheme() }
