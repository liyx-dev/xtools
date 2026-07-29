package com.liyx.xtools.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material.icons.filled.Merge
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GenerationPipelineCard(

    generating: Boolean,

    progress: Float,

    currentVoice: String,

    characterCount: Int,

    estimatedDuration: Long

) {

    Card(

        modifier = Modifier.fillMaxWidth(),

        shape = MaterialTheme.shapes.large,

        elevation = CardDefaults.cardElevation(

            defaultElevation = 6.dp

        )

    ) {

        Column(

            modifier = Modifier.padding(20.dp),

            verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {

            Text(

                text = "Generation Pipeline",

                style = MaterialTheme.typography.titleLarge

            )

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceBetween

            ) {

                Column {

                    Text("Voice")

                    Text(

                        currentVoice,

                        style = MaterialTheme.typography.titleMedium,

                        color = MaterialTheme.colorScheme.primary

                    )

                }

                Column(

                    horizontalAlignment = Alignment.End

                ) {

                    Text("Characters")

                    Text(

                        characterCount.toString(),

                        style = MaterialTheme.typography.titleMedium

                    )

                }

            }

            Text(

                text = "Estimated Audio: ${estimatedDuration / 1000}s",

                style = MaterialTheme.typography.bodyMedium

            )

            LinearProgressIndicator(

                progress = { progress },

                modifier = Modifier.fillMaxWidth()

            )

            PipelineStep(

                Icons.Default.CheckCircle,

                "Script Analysed"

            )

            PipelineStep(

                Icons.Default.AutoAwesome,

                "Smart Chunk Engine"

            )

            PipelineStep(

                Icons.Default.GraphicEq,

                if (generating)
                    "Generating Voice..."
                else
                    "Waiting"

            )

            PipelineStep(

                Icons.Default.Merge,

                "Merge Audio"

            )

            PipelineStep(

                Icons.Default.HourglassTop,

                "Export"

            )

        }

    }

}

@Composable
private fun PipelineStep(

    icon: androidx.compose.ui.graphics.vector.ImageVector,

    title: String

) {

    Row(

        verticalAlignment = Alignment.CenterVertically

    ) {

        Icon(

            imageVector = icon,

            contentDescription = null,

            tint = MaterialTheme.colorScheme.primary

        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(

            text = title,

            style = MaterialTheme.typography.bodyMedium

        )

    }

}
