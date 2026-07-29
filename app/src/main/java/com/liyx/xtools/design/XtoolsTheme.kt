package com.liyx.xtools.design

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColors = darkColorScheme(

    primary = XtoolsColors.Primary,

    secondary = XtoolsColors.Secondary,

    tertiary = XtoolsColors.Accent,

    background = XtoolsColors.Background,

    surface = XtoolsColors.Surface,

    error = XtoolsColors.Error

)

private val LightColors = lightColorScheme(

    primary = XtoolsColors.Primary,

    secondary = XtoolsColors.Secondary,

    tertiary = XtoolsColors.Accent,

    background = XtoolsColors.Background,

    surface = XtoolsColors.Surface,

    error = XtoolsColors.Error

)

@Composable
fun XtoolsTheme(

    darkTheme: Boolean = isSystemInDarkTheme(),

    content: @Composable () -> Unit

) {

    MaterialTheme(

        colorScheme = if (darkTheme)
            DarkColors
        else
            LightColors,

        typography = XtoolsTypography,

        shapes = XtoolsShapes,

        content = content

    )

}
