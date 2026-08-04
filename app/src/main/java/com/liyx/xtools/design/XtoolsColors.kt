package com.liyx.xtools.design

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

object XtoolsColors {

    /* =====================================================
     * BRAND
     * ===================================================== */

    val BrandGreen = Color(0xFF22C55E)
    val BrandGreenDark = Color(0xFF15803D)
    val BrandGreenLight = Color(0xFF4ADE80)

    val FacebookBlue = Color(0xFF1877F2)
    val FacebookBlueLight = Color(0xFF60A5FA)

    val YoutubeRed = Color(0xFFFF3B30)
    val YoutubeRedDark = Color(0xFFD32F2F)

    val PremiumGold = Color(0xFFFFC107)
    val PremiumGoldLight = Color(0xFFFFE082)

    val PurpleAccent = Color(0xFF7C4DFF)

    /* =====================================================
     * BACKGROUND
     * ===================================================== */

    val Background = Color(0xFF0D1117)

    val BackgroundSecondary = Color(0xFF111827)

    val Surface = Color(0xFF161B22)

    val Surface2 = Color(0xFF1C2128)

    val Surface3 = Color(0xFF222933)

    /* =====================================================
     * CARDS
     * ===================================================== */

    val Card = Surface

    val CardElevated = Color(0xFF262F3B)

    val CardBorder = Color(0xFF313A46)

    val Divider = Color(0xFF2D3748)

    /* =====================================================
     * TEXT
     * ===================================================== */

    val TextPrimary = Color.White

    val TextSecondary = Color(0xFFB8BDC8)

    val TextMuted = Color(0xFF8A94A6)

    val TextDisabled = Color(0xFF5F6978)

    /* =====================================================
     * STATUS
     * ===================================================== */

    val Success = BrandGreen

    val Error = YoutubeRed

    val Warning = PremiumGold

    val Info = FacebookBlue

    /* =====================================================
     * PROVIDERS
     * ===================================================== */

    val AndroidProvider = Color(0xFF3DDC84)

    val PiperProvider = Color(0xFF00BCD4)

    val KokoroProvider = Color(0xFF9C27B0)

    val ElevenLabsProvider = Color(0xFF7C4DFF)

    /* =====================================================
     * VOICE BADGES
     * ===================================================== */

    val Offline = BrandGreen

    val Online = FacebookBlue

    val Premium = PremiumGold

    val Female = Color(0xFFFF6FB5)

    val Male = Color(0xFF42A5F5)

    /* =====================================================
     * PLAYER
     * ===================================================== */

    val Playing = BrandGreen

    val Paused = PremiumGold

    val Recording = YoutubeRed

    /* =====================================================
     * BUTTONS
     * ===================================================== */

    val PrimaryButton = BrandGreen

    val SecondaryButton = FacebookBlue

    val DangerButton = YoutubeRed

    /* =====================================================
     * SLIDERS
     * ===================================================== */

    val SliderActive = BrandGreen

    val SliderInactive = Color(0xFF374151)

    /* =====================================================
     * PROGRESS
     * ===================================================== */

    val Progress = BrandGreen

    val ProgressTrack = Color(0xFF2D3748)

    /* =====================================================
     * ICONS
     * ===================================================== */

    val IconPrimary = TextPrimary

    val IconSecondary = TextSecondary

    /* =====================================================
     * GRADIENTS
     * ===================================================== */

    val GreenGradient = Brush.horizontalGradient(

        listOf(

            BrandGreen,

            BrandGreenLight

        )

    )

    val BlueGradient = Brush.horizontalGradient(

        listOf(

            FacebookBlue,

            FacebookBlueLight

        )

    )

    val GoldGradient = Brush.horizontalGradient(

        listOf(

            PremiumGold,

            PremiumGoldLight

        )

    )

    val HeroGradient = Brush.verticalGradient(

        listOf(

            Background,

            Surface,

            Surface2

        )

    )

    val PremiumGradient = Brush.linearGradient(

        listOf(

            BrandGreen,

            FacebookBlue,

            PurpleAccent

        )

    )
}
