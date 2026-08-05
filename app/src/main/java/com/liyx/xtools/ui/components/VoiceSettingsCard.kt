package com.liyx.xtools.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Restore
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.liyx.xtools.design.components.XSlider

@Composable
fun VoiceSettingsCard(

    speed: Float,

    pitch: Float,

    selectedVoice: String,

    modifier: Modifier = Modifier,

    onSpeedChanged: (Float) -> Unit,

    onPitchChanged: (Float) -> Unit,

    onReset: (() -> Unit)? = null

) {

    Card(

        modifier = modifier.fillMaxWidth(),

        shape = MaterialTheme.shapes.extraLarge

    ) {

        Column(

            modifier = Modifier.padding(20.dp)

        ) {

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceBetween,

                verticalAlignment = Alignment.CenterVertically

            ) {

                Column {

                    Text(

                        "Voice Studio",

                        style = MaterialTheme.typography.titleLarge

                    )

                Text(
    text = selectedVoice,
    style = MaterialTheme.typography.bodyMedium,
    color = MaterialTheme.colorScheme.primary,
    maxLines = 1,
    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
)

                FilledIconButton(

                    onClick = {

                        onReset?.invoke()

                    },

                    shape = CircleShape

                ) {

                    Icon(

                        Icons.Default.Restore,

                        contentDescription = null

                    )

                }

            }

            Spacer(

                Modifier.height(24.dp)

            )

            Row(

                verticalAlignment = Alignment.CenterVertically

            ) {

                Icon(

                    Icons.Default.Speed,

                    contentDescription = null,

                    tint = MaterialTheme.colorScheme.primary

                )

                Spacer(

                    Modifier.width(10.dp)

                )

                Text(

                    "Speaking Speed",

                    style = MaterialTheme.typography.titleMedium

                )

            }

            Spacer(

                Modifier.height(10.dp)

            )

            XSlider(

                value = speed,

                onValueChange = onSpeedChanged,

                valueRange = 0.5f..2f,

                steps = 14,

                valueText = "${String.format("%.1f", speed)}×"

            )

            Spacer(

                Modifier.height(24.dp)

            )

            Row(

                verticalAlignment = Alignment.CenterVertically

            ) {

                Icon(

                    Icons.Default.GraphicEq,

                    contentDescription = null,

                    tint = MaterialTheme.colorScheme.secondary

                )

                Spacer(

                    Modifier.width(10.dp)

                )

                Text(

                    "Voice Pitch",

                    style = MaterialTheme.typography.titleMedium

                )

            }

            Spacer(

                Modifier.height(10.dp)

            )

            XSlider(

                value = pitch,

                onValueChange = onPitchChanged,

                valueRange = 0.5f..2f,

                steps = 14,

                valueText = "${String.format("%.1f", pitch)}×"

            )

            Spacer(

                Modifier.height(28.dp)

            )

            HorizontalDivider()

            Spacer(

                Modifier.height(18.dp)

            )

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceEvenly

            ) {

                AssistChip(

                    onClick = {},

                    label = {

                        Text("Studio")

                    },

                    leadingIcon = {

                        Icon(

                            Icons.Default.Tune,

                            null

                        )

                    }

                )

                AssistChip(

                    onClick = {},

                    label = {

                        Text("Natural")

                    }

                )

                AssistChip(

                    onClick = {},

                    label = {

                        Text("HD")

                    }

                )

            }

        }

    }


}
