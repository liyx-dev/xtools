package com.liyx.xtools.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScriptAnalysisCard(

    characterCount: Int,

    estimatedDuration: Long,

    chunkCount: Int,

    wordCount: Int,

    paragraphCount: Int

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

            verticalArrangement = Arrangement.spacedBy(18.dp)

        ) {

            Row(

                verticalAlignment = Alignment.CenterVertically

            ) {

                Icon(

                    imageVector = Icons.Default.Analytics,

                    contentDescription = null,

                    tint = MaterialTheme.colorScheme.primary

                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(

                    "Script Analysis",

                    style = MaterialTheme.typography.titleLarge

                )

            }

            AnalysisRow("Characters", characterCount.toString())

            AnalysisRow("Words", wordCount.toString())

            AnalysisRow("Paragraphs", paragraphCount.toString())

            AnalysisRow(
                "Estimated Audio",
                formatDuration(estimatedDuration)
            )

            AnalysisRow(
                "Estimated Chunks",
                chunkCount.toString()
            )

        }

    }

}

@Composable
private fun AnalysisRow(

    title: String,

    value: String

) {

    Row(

        modifier = Modifier.fillMaxWidth(),

        horizontalArrangement = Arrangement.SpaceBetween

    ) {

        Text(

            title,

            style = MaterialTheme.typography.bodyMedium

        )

        Text(

            value,

            style = MaterialTheme.typography.titleMedium,

            color = MaterialTheme.colorScheme.primary

        )

    }

}

private fun formatDuration(

    duration: Long

): String {

    val totalSeconds = duration / 1000

    val minutes = totalSeconds / 60

    val seconds = totalSeconds % 60

    return "${minutes}m ${seconds}s"

}
