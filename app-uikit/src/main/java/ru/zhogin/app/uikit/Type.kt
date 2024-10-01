package ru.zhogin.app.uikit

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val SFFont = FontFamily(
    Font(R.font.sf_pro_display_regular, FontWeight.Normal),
    Font(R.font.sf_pro_display_medium, FontWeight.Medium),
    Font(R.font.sf_pro_display_semibold, FontWeight.SemiBold),
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)


val Typography.Title1New: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = SFFont,
            fontWeight = FontWeight.SemiBold,
            fontSize = 22.sp,
            color = White
        )
    }

val Typography.Title2New: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = SFFont,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            color = White
        )
    }
val Typography.Title3New: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = SFFont,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = White
        )
    }
val Typography.Title4New: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = SFFont,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = White
        )
    }
val Typography.Text1New: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = SFFont,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = White
        )
    }
val Typography.ButtonText1New: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = SFFont,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = White
        )
    }
val Typography.ButtonText2New: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = SFFont,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = White
        )
    }
val Typography.TabTextNew: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = SFFont,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            color = White
        )
    }
val Typography.NumberNew: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = SFFont,
            fontWeight = FontWeight.Normal,
            fontSize = 9.sp,
            color = White
        )
    }
