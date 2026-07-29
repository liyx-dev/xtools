package com.liyx.xtools.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private val voices = listOf(
    "Emma",
    "David",
    "Sophia",
    "Michael",
    "Narrator",
    "Studio"
)

@Composable
fun VoiceSelectorCard(

    selectedVoice: String,

    onVoiceSelected: (String) -> Unit

) {

    Column {

        Text(

            text = "Voice",

            style = MaterialTheme.typography.titleMedium

        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(

            horizontalArrangement = Arrangement.spacedBy(12.dp)

        ) {

            items(voices) { voice ->

                val selected = voice == selectedVoice

                Card(

                    modifier = Modifier.clickable {

                        onVoiceSelected(voice)

                    },

                    colors = CardDefaults.cardColors(

                        containerColor =
                        if (selected)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.surfaceVariant

                    )

                ) {

                    Column(

                        modifier = Modifier
                            .padding(18.dp),

                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {

                        Icon(

                            imageVector = Icons.Default.GraphicEq,

                            contentDescription = null,

                            tint =
                            if (selected)
                                MaterialTheme.colorScheme.onPrimary
                            else
                                MaterialTheme.colorScheme.primary

                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(

                            text = voice,

                            color =
                            if (selected)
                                MaterialTheme.colorScheme.onPrimary
                            else
                                MaterialTheme.colorScheme.onSurface,

                            style = MaterialTheme.typography.bodyMedium

                        )

                    }

                }

            }

        }

    }

}
