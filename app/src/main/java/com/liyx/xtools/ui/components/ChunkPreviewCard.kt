package com.liyx.xtools.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.liyx.xtools.core.models.Chunk

@Composable
fun ChunkPreviewCard(

    chunks: List<Chunk>

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

                text = "Chunk Preview",

                style = MaterialTheme.typography.titleLarge,

                fontWeight = FontWeight.Bold

            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(

                text = "${chunks.size} chunk(s) prepared",

                style = MaterialTheme.typography.bodyMedium

            )

            Spacer(modifier = Modifier.height(16.dp))

            if (chunks.isEmpty()) {

                Text(

                    text = "No chunks available yet.",

                    style = MaterialTheme.typography.bodyMedium

                )

            } else {

                LazyColumn(

                    modifier = Modifier.heightIn(max = 300.dp),

                    verticalArrangement = Arrangement.spacedBy(12.dp)

                ) {

                    items(chunks) { chunk ->

                        Column {

                            Text(

                                text = "Chunk ${chunk.order}",

                                style = MaterialTheme.typography.titleMedium

                            )

                            Text(

                                text = "${chunk.characterCount} characters",

                                style = MaterialTheme.typography.bodySmall

                            )

                            Text(

                                text = "${chunk.estimatedDurationMs / 1000}s",

                                style = MaterialTheme.typography.bodySmall

                            )

                            HorizontalDivider(

                                modifier = Modifier.padding(top = 8.dp)

                            )

                        }

                    }

                }

            }

        }

    }

}
