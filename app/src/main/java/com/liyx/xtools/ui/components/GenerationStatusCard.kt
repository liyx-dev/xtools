package com.liyx.xtools.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GenerationStatusCard(

    generating: Boolean,

    progress: Float

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

            if (generating) {

                Text(
                    "Generating audio...",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.fillMaxWidth()
                )

            } else {

                Text(
                    "No audio generated yet.",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    "Your generated audio will appear here.",
                    style = MaterialTheme.typography.bodySmall
                )

            }

        }

    }

}
