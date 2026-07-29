package com.liyx.xtools.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun VoiceSettingsCard(

    speed: Float,

    pitch: Float,

    selectedVoice: String,

    onSpeedChanged: (Float) -> Unit,

    onPitchChanged: (Float) -> Unit

) {

    Card(

        modifier = Modifier.fillMaxWidth(),

        shape = MaterialTheme.shapes.large,

        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )

    ) {

        Column(

            modifier = Modifier.padding(20.dp)

        ) {

            Text(

                text = "Voice Settings",

                style = MaterialTheme.typography.titleLarge

            )

            Spacer(modifier = Modifier.height(18.dp))

            Text(

                text = "Selected Voice",

                style = MaterialTheme.typography.labelMedium

            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(

                text = selectedVoice,

                style = MaterialTheme.typography.bodyLarge,

                color = MaterialTheme.colorScheme.primary

            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(

                text = "Speech Speed",

                style = MaterialTheme.typography.labelMedium

            )

            Slider(

                value = speed,

                onValueChange = onSpeedChanged,

                valueRange = 0.5f..2f

            )

            Spacer(modifier = Modifier.height(18.dp))

            Text(

                text = "Pitch",

                style = MaterialTheme.typography.labelMedium

            )

            Slider(

                value = pitch,

                onValueChange = onPitchChanged,

                valueRange = 0.5f..2f

            )

        }

    }

}
