package com.liyx.xtools.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
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
fun HeroSection() {

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        tonalElevation = 6.dp
    ) {

        Box(
            modifier = Modifier
                .background(
                    Brush.verticalGradient(
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
                        .size(72.dp)
                        .clip(CircleShape)
                        .background(XtoolsColors.Surface),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = XtoolsColors.Primary,
                        modifier = Modifier.size(34.dp)
                    )

                }

                Spacer(modifier = Modifier.width(20.dp))

                Column {

                    Text(
                        text = "Xtools",
                        style = MaterialTheme.typography.headlineMedium,
                        color = XtoolsColors.Surface
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "AI Productivity Suite",
                        style = MaterialTheme.typography.bodyLarge,
                        color = XtoolsColors.Surface.copy(alpha = .90f)
                    )

                }

            }

        }

    }

}
