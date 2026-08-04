package com.liyx.xtools.design

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColors = darkColorScheme(

    primary = XGreen,

    secondary = XBlue,

    tertiary = XYellow,

    background = XBackground,

    surface = XSurface,

    surfaceVariant = XSurface2,

    onPrimary = XWhite,

    onSecondary = XWhite,

    onBackground = XWhite,

    onSurface = XWhite,

    error = XRed

)

private val LightColors = lightColorScheme(

    primary = XGreen,

    secondary = XBlue,

    tertiary = XYellow,

    background = XWhite,

    surface = XWhite,

    onPrimary = XWhite,

    onSecondary = XWhite,

    onBackground = XBlack,

    onSurface = XBlack,

    error = XRed

)

@Composable
fun XtoolsTheme(

    darkTheme: Boolean = true,

    content: @Composable () -> Unit

) {

    MaterialTheme(

        colorScheme = if (darkTheme) DarkColors else LightColors,

        typography = XtoolsTypography,

        shapes = XtoolsShapes,

        content = content

    )

}
