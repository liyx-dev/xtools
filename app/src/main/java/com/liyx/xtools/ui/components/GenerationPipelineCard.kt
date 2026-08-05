package com.liyx.xtools.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material.icons.filled.Merge
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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

        shape = MaterialTheme.shapes.extraLarge,

        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)

    ) {

        Column(

            modifier = Modifier.padding(22.dp)

        ) {

            Text(

                text = "⚙ Generation Pipeline",

                style = MaterialTheme.typography.titleLarge,

                color = MaterialTheme.colorScheme.primary,

                fontWeight = FontWeight.Bold

            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(

                text = "Real-time narration rendering pipeline",

                style = MaterialTheme.typography.bodyMedium,

                color = MaterialTheme.colorScheme.onSurfaceVariant

            )

            Spacer(modifier = Modifier.height(22.dp))

Row(
    modifier = Modifier.fillMaxWidth()
) {

    Box(
        modifier = Modifier.weight(2f)
    ) {
        PipelineInfo(
            "Voice",
            currentVoice
        )
    }

    Box(
        modifier = Modifier.weight(1f)
    ) {
        PipelineInfo(
            "Characters",
            characterCount.toString()
        )
    }

    Box(
        modifier = Modifier.weight(1f)
    ) {
        PipelineInfo(
            "Duration",
            "${estimatedDuration / 1000}s"
        )
    }

}

           Spacer(modifier = Modifier.height(24.dp))

            LinearProgressIndicator(

                progress = { progress },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),

            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(

                text = "${(progress * 100).toInt()}% Complete",

                style = MaterialTheme.typography.labelLarge,

                color = MaterialTheme.colorScheme.primary

            )

            Spacer(modifier = Modifier.height(24.dp))

            PipelineStage(

                icon = Icons.Default.CheckCircle,

                title = "Script Analysis",

                completed = true,

                active = false

            )

            PipelineStage(

                icon = Icons.Default.AutoAwesome,

                title = "Smart Chunk Engine",

                completed = progress > 0.20f,

                active = generating && progress < 0.30f

            )

            PipelineStage(

                icon = Icons.Default.GraphicEq,

                title = "Voice Synthesis",

                completed = progress > 0.60f,

                active = generating && progress in 0.30f..0.60f

            )

            PipelineStage(

                icon = Icons.Default.Merge,

                title = "Audio Merge",

                completed = progress > 0.85f,

                active = generating && progress in 0.60f..0.85f

            )

            PipelineStage(

                icon = Icons.Default.HourglassTop,

                title = "Export",

                completed = progress >= 1f,

                active = generating && progress > 0.85f

            )

        }

    }

}

@Composable
private fun PipelineInfo(

    label: String,

    value: String

) {

    Column(

        horizontalAlignment = Alignment.CenterHorizontally

    ) {

     Text(
    text = value,
    style = MaterialTheme.typography.titleMedium,
    fontWeight = FontWeight.Bold,
    color = MaterialTheme.colorScheme.primary,
    maxLines = 1,
    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
)

        Text(

            text = label,

            style = MaterialTheme.typography.bodySmall,

            color = MaterialTheme.colorScheme.onSurfaceVariant

        )

    }

}

@Composable
private fun PipelineStage(

    icon: androidx.compose.ui.graphics.vector.ImageVector,

    title: String,

    completed: Boolean,

    active: Boolean

) {

    val color = when {

        completed -> Color(0xFF28A745)

        active -> MaterialTheme.colorScheme.primary

        else -> MaterialTheme.colorScheme.outline

    }

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),

        verticalAlignment = Alignment.CenterVertically

    ) {

        Box(

            modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .background(color.copy(alpha = .15f)),

            contentAlignment = Alignment.Center

        ) {

            Icon(

                imageVector = icon,

                contentDescription = null,

                tint = color

            )

        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(

            modifier = Modifier.weight(1f)

        ) {

            Text(

                text = title,

                style = MaterialTheme.typography.titleMedium,

                fontWeight = FontWeight.SemiBold

            )

            Text(

                text = when {

                    completed -> "Completed"

                    active -> "In Progress"

                    else -> "Waiting"

                },

                style = MaterialTheme.typography.bodySmall,

                color = color

            )

        }

    }

}
