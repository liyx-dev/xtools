package com.liyx.xtools.design.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.liyx.xtools.design.XShape
import com.liyx.xtools.design.XtoolsColors

@Composable
fun XCard(

    modifier: Modifier = Modifier,

    shape: Shape = XShape.Card,

    padding: PaddingValues = PaddingValues(18.dp),

    content: @Composable () -> Unit

) {

    Card(

        modifier = modifier.fillMaxWidth(),

        shape = shape,

        border = BorderStroke(

            1.dp,

            XtoolsColors.CardBorder

        ),

        colors = CardDefaults.cardColors(

            containerColor = XtoolsColors.Card

        ),

        elevation = CardDefaults.cardElevation(

            defaultElevation = 8.dp

        )

    ) {

        Box(

            modifier = Modifier.padding(padding)

        ) {

            content()

        }

    }

}  
