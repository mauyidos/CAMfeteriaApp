package com.example.camfeteriaapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.sp
import com.example.camfeteriaapp.R

val April = FontFamily(
    Font(R.font.april_regular, FontWeight.Normal),
    Font(R.font.april_thin, FontWeight.Thin),
    Font(R.font.april_light, FontWeight.Light),
    Font(R.font.april_extralight, FontWeight.ExtraLight)
)

val Cardo = FontFamily(
    Font(R.font.cardo_regular, FontWeight.Normal),
    Font(R.font.cardo_bold, FontWeight.Bold)
)

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = April,
        fontSize = 22.sp
    ),

    bodyMedium = TextStyle(
        fontFamily = April,
        fontSize = 16.sp
    ),

    bodySmall = TextStyle(
        fontFamily = April,
        fontSize = 13.sp
    ),

    labelLarge = TextStyle(
        fontFamily = April,
        fontSize = 14.sp
    ),

    displayLarge = TextStyle(
        fontFamily = Cardo,
        fontSize = 22.sp
    ),

    displayMedium = TextStyle(
        fontFamily = Cardo,
        fontSize = 16.sp
    ),

    displaySmall = TextStyle(
        fontFamily = Cardo,
        fontSize = 13.sp
    ),
)