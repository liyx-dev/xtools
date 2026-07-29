package com.liyx.xtools.ui.library.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.liyx.xtools.core.player.models.AudioRecording

@Composable
fun AudioRecordingCard(

    recording: AudioRecording

) {

    Card(

        modifier = Modifier.fillMaxWidth()

    ) {

        Column(

            modifier = Modifier.padding(16.dp)

        ) {

            Text(

                text = recording.title,

                style = MaterialTheme.typography.titleMedium

            )

            Text(

                text = recording.filePath,

                style = MaterialTheme.typography.bodySmall

            )

            Text(

                text = "Duration: ${recording.duration} ms",

                style = MaterialTheme.typography.bodySmall

            )

        }

    }

}
