package com.liyx.xtools.design.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.liyx.xtools.design.XtoolsColors

@Composable
fun SecondaryButton(

    text: String,

    onClick: () -> Unit,

    modifier: Modifier = Modifier,

    enabled: Boolean = true

) {

    OutlinedButton(

        onClick = onClick,

        enabled = enabled,

        modifier = modifier
            .fillMaxWidth()
            .height(58.dp),

        shape = RoundedCornerShape(18.dp),

        border = BorderStroke(

            1.dp,

            XtoolsColors.SecondaryButton

        ),

        colors = ButtonDefaults.outlinedButtonColors(

            contentColor = XtoolsColors.SecondaryButton

        )

    ) {

        Text(

            text = text,

            style = MaterialTheme.typography.titleMedium

        )

    }

}
