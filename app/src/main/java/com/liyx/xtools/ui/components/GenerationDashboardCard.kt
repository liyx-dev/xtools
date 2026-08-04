package com.liyx.xtools.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

        shape = MaterialTheme.shapes.extraLarge,

        elevation = CardDefaults.cardElevation(

            defaultElevation = 8.dp

        )

    ) {

        Column(

            modifier = Modifier.padding(22.dp)

        ) {

            Text(

                text = "📈 Production Dashboard",

                style = MaterialTheme.typography.titleLarge,

                fontWeight = FontWeight.Bold,

                color = MaterialTheme.colorScheme.primary

            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(

                text = "Live AI voice generation statistics",

                style = MaterialTheme.typography.bodyMedium,

                color = MaterialTheme.colorScheme.onSurfaceVariant

            )

            Spacer(modifier = Modifier.height(22.dp))

            LinearProgressIndicator(

                progress = { progress },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp),

            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(

                text = "${(progress * 100).toInt()}% Completed",

                style = MaterialTheme.typography.labelLarge,

                color = MaterialTheme.colorScheme.primary

            )

            Spacer(modifier = Modifier.height(22.dp))

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.spacedBy(14.dp)

            ) {

                DashboardTile(

                    modifier = Modifier.weight(1f),

                    title = "Chunks",

                    value = "$processedChunks / $totalChunks",

                    color = Color(0xFF28A745)

                )

                DashboardTile(

                    modifier = Modifier.weight(1f),

                    title = "Characters",

                    value = "$processedCharacters / $totalCharacters",

                    color = Color(0xFF1877F2)

                )

            }

            Spacer(modifier = Modifier.height(14.dp))

            DashboardStatus(

                generating = generating,

                progress = progress

            )

        }

    }

}

@Composable
private fun DashboardTile(

    modifier: Modifier = Modifier,

    title: String,

    value: String,

    color: Color

) {

    Column(

        modifier = modifier
            .background(

                color.copy(alpha = .08f),

                RoundedCornerShape(18.dp)

            )
            .padding(18.dp),

        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text(

            text = value,

            style = MaterialTheme.typography.titleLarge,

            fontWeight = FontWeight.Bold,

            color = color

        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(

            text = title,

            style = MaterialTheme.typography.bodySmall,

            color = MaterialTheme.colorScheme.onSurfaceVariant

        )

    }

}

@Composable
private fun DashboardStatus(

    generating: Boolean,

    progress: Float

) {

    val status = when {

        progress >= 1f ->
            "✅ Voice generation completed"

        generating ->
            "🎙 AI is generating narration..."

        else ->
            "💤 Waiting for script"

    }

    val color = when {

        progress >= 1f ->
            Color(0xFF28A745)

        generating ->
            MaterialTheme.colorScheme.primary

        else ->
            MaterialTheme.colorScheme.outline

    }

    Column(

        modifier = Modifier.fillMaxWidth()

    ) {

        Text(

            text = "Current Status",

            style = MaterialTheme.typography.labelLarge,

            fontWeight = FontWeight.Bold

        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(

            text = status,

            style = MaterialTheme.typography.bodyLarge,

            color = color

        )

    }

}
