package com.liyx.xtools.design

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColors = darkColorScheme(

    primary = Color(0xFF3B82F6),

    secondary = Color(0xFF8B5CF6),

    tertiary = Color(0xFF06B6D4),

    background = Color(0xFF0B1120),

    surface = Color(0xFF111827)

)

private val LightColors = lightColorScheme(

    primary = Color(0xFF2563EB),

    secondary = Color(0xFF7C3AED),

    tertiary = Color(0xFF0891B2)

)

@Composable
fun XtoolsTheme(

    darkTheme: Boolean = isSystemInDarkTheme(),

    content: @Composable () -> Unit

) {

    MaterialTheme(

        colorScheme = if (darkTheme) DarkColors else LightColors,

        typography = XtoolsTypography,

        shapes = XtoolsShapes,

        content = content

    )

}
