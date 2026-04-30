package com.example.camfeteriaapp.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val AppColorScheme = lightColorScheme(
    primary = PrimaryStart,
    secondary = Secondary,
    tertiary = Coffee,
    background = Background,
    onBackground = TextPrimary,
    error = ErrorColor
)

@Composable
fun CAMfeteriaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = Typography,
        content = content
    )
}