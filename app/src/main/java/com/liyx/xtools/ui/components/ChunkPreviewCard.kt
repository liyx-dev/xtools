package com.liyx.xtools.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.liyx.xtools.core.models.Chunk

@Composable
fun ChunkPreviewCard(

    chunks: List<Chunk>

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

                text = "🧩 Smart Chunk Preview",

                style = MaterialTheme.typography.titleLarge,

                fontWeight = FontWeight.Bold,

                color = MaterialTheme.colorScheme.primary

            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(

                text = "${chunks.size} chunk(s) prepared for voice generation",

                style = MaterialTheme.typography.bodyMedium,

                color = MaterialTheme.colorScheme.onSurfaceVariant

            )

            Spacer(modifier = Modifier.height(20.dp))

            if (chunks.isEmpty()) {

                Box(

                    modifier = Modifier
                        .fillMaxWidth()
                        .background(

                            MaterialTheme.colorScheme.surfaceVariant,

                            RoundedCornerShape(20.dp)

                        )
                        .padding(30.dp),

                    contentAlignment = Alignment.Center

                ) {

                    Text(

                        "No chunks generated yet.",

                        color = MaterialTheme.colorScheme.onSurfaceVariant

                    )

                }

            } else {

                LazyColumn(

                    modifier = Modifier.heightIn(max = 450.dp),

                    verticalArrangement = Arrangement.spacedBy(14.dp)

                ) {

                    items(chunks) { chunk ->

                        ChunkItem(chunk)

                    }

                }

            }

        }

    }

}

@Composable
private fun ChunkItem(

    chunk: Chunk

) {

    var expanded by remember {

        mutableStateOf(false)

    }

    ElevatedCard(

        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(20.dp)

    ) {

        Column(

            modifier = Modifier
                .fillMaxWidth()
                .clickable {

                    expanded = !expanded

                }
                .padding(18.dp)

        ) {

            Row(

                verticalAlignment = Alignment.CenterVertically

            ) {

                Icon(

                    imageVector = Icons.Default.Description,

                    contentDescription = null,

                    tint = MaterialTheme.colorScheme.primary

                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(

                    modifier = Modifier.weight(1f)

                ) {

                    Text(

                        "Chunk ${chunk.order}",

                        fontWeight = FontWeight.Bold,

                        style = MaterialTheme.typography.titleMedium

                    )

                    Text(

                        "${chunk.characterCount} characters",

                        style = MaterialTheme.typography.bodySmall,

                        color = MaterialTheme.colorScheme.onSurfaceVariant

                    )

                }

                Surface(

                    color = Color(0xFF28A745),

                    shape = RoundedCornerShape(50)

                ) {

                    Text(

                        "READY",

                        modifier = Modifier.padding(

                            horizontal = 10.dp,

                            vertical = 4.dp

                        ),

                        color = Color.White,

                        style = MaterialTheme.typography.labelSmall

                    )

                }

                Spacer(modifier = Modifier.width(8.dp))

                Icon(

                    imageVector =

                        if (expanded)

                            Icons.Default.ExpandLess

                        else

                            Icons.Default.ExpandMore,

                    contentDescription = null

                )

            }

            Spacer(modifier = Modifier.height(14.dp))

            Row(

                horizontalArrangement = Arrangement.SpaceBetween,

                modifier = Modifier.fillMaxWidth()

            ) {

                ChunkMetric(

                    "Duration",

                    "${chunk.estimatedDurationMs / 1000}s"

                )

                ChunkMetric(

                    "Characters",

                    chunk.characterCount.toString()

                )

            }

            if (expanded) {

                Spacer(modifier = Modifier.height(16.dp))

                Divider()

                Spacer(modifier = Modifier.height(12.dp))

                Text(

                    text = chunk.text,

                    style = MaterialTheme.typography.bodyMedium,

                    maxLines = 8,

                    overflow = TextOverflow.Ellipsis

                )

            }

        }

    }

}

@Composable
private fun ChunkMetric(

    title: String,

    value: String

) {

    Column(

        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text(

            value,

            style = MaterialTheme.typography.titleMedium,

            fontWeight = FontWeight.Bold,

            color = MaterialTheme.colorScheme.primary

        )

        Text(

            title,

            style = MaterialTheme.typography.bodySmall,

            color = MaterialTheme.colorScheme.onSurfaceVariant

        )

    }

}  
