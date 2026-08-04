package com.liyx.xtools.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.FastForward
import androidx.compose.material.icons.filled.FastRewind
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
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

    var playing by remember { mutableStateOf(false) }

    var sharing by remember { mutableStateOf(false) }

    var exporting by remember { mutableStateOf(false) }

    var progress by remember { mutableFloatStateOf(0.35f) }

    ElevatedCard(

        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(28.dp)

    ) {

        Column(

            modifier = Modifier.padding(20.dp)

        ) {

            Row(

                verticalAlignment = Alignment.CenterVertically

            ) {

                Box(

                    modifier = Modifier

                        .size(78.dp)

                        .clip(CircleShape)

                        .background(

                            Brush.linearGradient(

                                listOf(

                                    MaterialTheme.colorScheme.primary,

                                    MaterialTheme.colorScheme.secondary

                                )

                            )

                        ),

                    contentAlignment = Alignment.Center

                ) {

                    Text(

                        "🎤",

                        style = MaterialTheme.typography.headlineMedium

                    )

                }

                Spacer(Modifier.width(16.dp))

                Column {

                    Text(

                        "Generated Audio",

                        style = MaterialTheme.typography.titleLarge,

                        fontWeight = FontWeight.Bold

                    )

                    Text(

                        "Studio Quality",

                        color = MaterialTheme.colorScheme.primary

                    )

                }

            }

            Spacer(Modifier.height(20.dp))

            LinearProgressIndicator(

                progress = { progress },

                modifier = Modifier.fillMaxWidth()

            )

            Spacer(Modifier.height(8.dp))

            Text(

                "01:32 / 08:42",

                style = MaterialTheme.typography.bodySmall

            )

            Spacer(Modifier.height(20.dp))

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceEvenly,

                verticalAlignment = Alignment.CenterVertically

            ) {

                FilledIconButton(onClick = { }) {

                    Icon(

                        Icons.Default.FastRewind,

                        null

                    )

                }

                FilledIconButton(

                    onClick = {

                        if (playing) {

                            playing = false

                            onPause()

                        } else {

                            playing = true

                            onPlay()

                        }

                    }

                ) {

                    Icon(

                        if (playing)

                            Icons.Default.Pause

                        else

                            Icons.Default.PlayArrow,

                        null

                    )

                }

                FilledIconButton(

                    onClick = {

                        playing = false

                        onStop()

                    }

                ) {

                    Text("■")

                }

                FilledIconButton(onClick = { }) {

                    Icon(

                        Icons.Default.FastForward,

                        null

                    )

                }

            }

            Spacer(Modifier.height(20.dp))

            AssistChip(

                onClick = {},

                label = { Text("Android TTS") }

            )

            Spacer(Modifier.height(6.dp))

            AssistChip(

                onClick = {},

                label = { Text("Offline Voice") }

            )

            Spacer(Modifier.height(24.dp))

            Row(

                horizontalArrangement = Arrangement.spacedBy(12.dp)

            ) {

                Button(

                    modifier = Modifier.weight(1f),

                    enabled = canShare && !sharing,

                    onClick = {

                        sharing = true

                        onShare()

                        sharing = false

                    }

                ) {

                    if (sharing)

                        CircularProgressIndicator(

                            modifier = Modifier.size(16.dp),

                            strokeWidth = 2.dp

                        )

                    else

                        Icon(Icons.Default.Share, null)

                    Spacer(Modifier.width(8.dp))

                    Text("Share")

                }

                Button(

                    modifier = Modifier.weight(1f),

                    enabled = canExport && !exporting,

                    onClick = {

                        exporting = true

                        onExport()

                        exporting = false

                    }

                ) {

                    if (exporting)

                        CircularProgressIndicator(

                            modifier = Modifier.size(16.dp),

                            strokeWidth = 2.dp

                        )

                    else

                        Icon(Icons.Default.Download, null)

                    Spacer(Modifier.width(8.dp))

                    Text("Export")

                }

            }

        }

    }

}
