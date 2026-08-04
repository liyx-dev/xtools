package com.liyx.xtools.design.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun XSlider(

    value: Float,

    onValueChange: (Float) -> Unit,

    modifier: Modifier = Modifier,

    valueRange: ClosedFloatingPointRange<Float> = 0.5f..2f,

    steps: Int = 14,

    valueText: String

) {

    Column(

        modifier = modifier.fillMaxWidth()

    ) {

        Row(

            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.End,

            verticalAlignment = Alignment.CenterVertically

        ) {

            Text(

                text = valueText,

                style = MaterialTheme.typography.labelLarge,

                color = MaterialTheme.colorScheme.primary

            )

        }

        Spacer(

            Modifier.height(8.dp)

        )

        Slider(

            value = value,

            onValueChange = onValueChange,

            valueRange = valueRange,

            steps = steps,

            colors = SliderDefaults.colors(

                thumbColor = MaterialTheme.colorScheme.primary,

                activeTrackColor = MaterialTheme.colorScheme.primary,

                inactiveTrackColor = MaterialTheme.colorScheme.surfaceVariant

            )

        )

    }

}
