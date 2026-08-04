package com.liyx.xtools.design

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColors = darkColorScheme(

    primary = XtoolsColors.BrandGreen,
    secondary = XtoolsColors.FacebookBlue,
    tertiary = XtoolsColors.PremiumGold,

    background = XtoolsColors.Background,
    surface = XtoolsColors.Surface,
    surfaceVariant = XtoolsColors.Surface2,

    onPrimary = XtoolsColors.TextPrimary,
    onSecondary = XtoolsColors.TextPrimary,
    onBackground = XtoolsColors.TextPrimary,
    onSurface = XtoolsColors.TextPrimary,

    error = XtoolsColors.Error

)

private val LightColors = lightColorScheme(

    primary = XtoolsColors.BrandGreen,
    secondary = XtoolsColors.FacebookBlue,
    tertiary = XtoolsColors.PremiumGold,

    background = androidx.compose.ui.graphics.Color.White,
    surface = androidx.compose.ui.graphics.Color.White,

    onPrimary = androidx.compose.ui.graphics.Color.White,
    onSecondary = androidx.compose.ui.graphics.Color.White,

    onBackground = androidx.compose.ui.graphics.Color.Black,
    onSurface = androidx.compose.ui.graphics.Color.Black,

    error = XtoolsColors.Error

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

