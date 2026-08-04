package com.liyx.xtools.design

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val XtoolsShapes = Shapes(

    /* Small controls */

    extraSmall = RoundedCornerShape(6.dp),

    small = RoundedCornerShape(12.dp),

    /* Buttons */

    medium = RoundedCornerShape(18.dp),

    /* Cards */

    large = RoundedCornerShape(22.dp),

    /* Hero cards */

    extraLarge = RoundedCornerShape(28.dp)

)

/* =====================================================
 * Premium reusable shapes
 * ===================================================== */

object XShape {

    val Button = RoundedCornerShape(18.dp)

    val Card = RoundedCornerShape(22.dp)

    val HeroCard = RoundedCornerShape(28.dp)

    val VoiceCard = RoundedCornerShape(24.dp)

    val BottomSheet = RoundedCornerShape(

        topStart = 30.dp,

        topEnd = 30.dp

    )

    val Dialog = RoundedCornerShape(26.dp)

    val TextField = RoundedCornerShape(18.dp)

    val Chip = RoundedCornerShape(50.dp)

    val SliderThumb = RoundedCornerShape(50)

}
