package uz.isheraliyev.project.ui.design.font

import androidx.annotation.FontRes
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import uz.isheraliyev.project.R.font.inter_regular as regular
import uz.isheraliyev.project.R.font.inter_medium as medium
import uz.isheraliyev.project.R.font.inter_semi_bold as semibold

val localFont = LocalFont(
    largeSemiBold = createText(
        font = semibold,
        size = 16.sp,
        weight = FontWeight.W600
    ),
    largeMedium = createText(
        font = medium,
        size = 16.sp,
        weight = FontWeight.W500
    ),
    largeRegular = createText(
        font = regular,
        size = 16.sp,
        weight = FontWeight.W400
    ),
    mediumSemiBold = createText(
        font = semibold,
        size = 14.sp,
        weight = FontWeight.W600
    ),
    mediumMedium = createText(
        font = medium,
        size = 14.sp,
        weight = FontWeight.W500
    ),
    mediumRegular = createText(
        font = regular,
        size = 14.sp,
        weight = FontWeight.W400
    ),
    smallSemiBold = createText(
        font = semibold,
        size = 12.sp,
        weight = FontWeight.W600
    ),
    smallMedium = createText(
        font = medium,
        size = 12.sp,
        weight = FontWeight.W500
    ),
    smallRegular = createText(
        font = regular,
        size = 12.sp,
        weight = FontWeight.W600
    )
)

private fun createText(
    @FontRes font: Int,
    size: TextUnit,
    weight: FontWeight
) = TextStyle(
    fontFamily = FontFamily(Font(font)),
    fontSize = size,
    fontWeight = weight,
)

val LocalCustomFont = staticCompositionLocalOf { localFont }
