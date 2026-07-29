package com.liyx.xtools.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.liyx.xtools.design.XtoolsColors

@Composable
fun VoiceHeader() {

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        tonalElevation = 6.dp
    ) {

        Box(
            modifier = Modifier
                .background(
                    Brush.horizontalGradient(
                        listOf(
                            XtoolsColors.Primary,
                            XtoolsColors.Secondary
                        )
                    )
                )
                .padding(24.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(68.dp)
                        .clip(CircleShape)
                        .background(XtoolsColors.Surface),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = Icons.Default.GraphicEq,
                        contentDescription = null,
                        tint = XtoolsColors.Primary,
                        modifier = Modifier.size(34.dp)
                    )

                }

                Spacer(modifier = Modifier.width(20.dp))

                Column {

                    Text(
                        text = "Voice Studio",
                        style = MaterialTheme.typography.headlineSmall,
                        color = XtoolsColors.Surface
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Turn text into natural speech.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = XtoolsColors.Surface.copy(alpha = .9f)
                    )

                }

            }

        }

    }

}
