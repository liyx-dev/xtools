package com.liyx.xtools.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GeneratedAudioCard(

    audioPath: String?,

    canPlay: Boolean,

    canShare: Boolean,

    canExport: Boolean,

    onPlay: () -> Unit,

    onPause: () -> Unit,

    onStop: () -> Unit,

    onShare: () -> Unit,

    onExport: () -> Unit

) {

    if (audioPath == null) return

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text("Generated Audio")

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(audioPath)

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Button(
                    enabled = canPlay,
                    onClick = onPlay
                ) {
                    Text("▶ Play")
                }

                Button(
                    enabled = canPlay,
                    onClick = onPause
                ) {
                    Text("⏸ Pause")
                }

                Button(
                    enabled = canPlay,
                    onClick = onStop
                ) {
                    Text("■ Stop")
                }

            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Button(
                    enabled = canShare,
                    onClick = onShare
                ) {
                    Text("Share")
                }

                Button(
                    enabled = canExport,
                    onClick = onExport
                ) {
                    Text("Export")
                }

            }

        }

    }

}
