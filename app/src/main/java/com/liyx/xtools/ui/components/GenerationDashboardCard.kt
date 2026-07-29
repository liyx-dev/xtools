package com.liyx.xtools.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun GenerationDashboardCard(

    progress: Float,

    processedChunks: Int,

    totalChunks: Int,

    processedCharacters: Int,

    totalCharacters: Int,

    generating: Boolean

) {

    Card(

        modifier = Modifier.fillMaxWidth(),

        shape = MaterialTheme.shapes.large,

        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )

    ) {

        Column(

            modifier = Modifier.padding(20.dp)

        ) {

            Text(

                text = "Generation Dashboard",

                style = MaterialTheme.typography.titleLarge,

                fontWeight = FontWeight.Bold

            )

            Spacer(modifier = Modifier.height(16.dp))

            LinearProgressIndicator(

                progress = { progress },

                modifier = Modifier.fillMaxWidth()

            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Chunks: $processedChunks / $totalChunks"
            )

            Text(
                "Characters: $processedCharacters / $totalCharacters"
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(

                if (generating)
                    "Status: Generating..."
                else
                    "Status: Ready",

                style = MaterialTheme.typography.bodyMedium

            )

        }

    }

}
