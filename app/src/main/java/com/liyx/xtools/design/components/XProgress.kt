package com.liyx.xtools.design.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun XProgress(
    progress: Float,
    title: String = "",
    message: String = ""
) {

    Card(

        modifier = Modifier.fillMaxWidth()

    ) {

        Column(

            modifier = Modifier.padding(20.dp)

        ) {

            if (title.isNotBlank()) {

                Text(

                    text = title,

                    style = MaterialTheme.typography.titleMedium

                )

            }

            if (message.isNotBlank()) {

                Text(

                    text = message,

                    style = MaterialTheme.typography.bodyMedium,

                    color = MaterialTheme.colorScheme.onSurfaceVariant,

                    modifier = Modifier.padding(top = 6.dp)

                )

            }

            LinearProgressIndicator(

                progress = { progress },

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)

            )

            Text(

                text = "${(progress * 100).toInt()}%",

                modifier = Modifier.padding(top = 10.dp),

                style = MaterialTheme.typography.labelMedium

            )

        }

    }

}
