package com.liyx.xtools.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.liyx.xtools.design.XtoolsColors

@Composable
fun VoiceHeader(

    title: String,

    subtitle: String,

    badge: String

) {

    Surface(

        modifier = Modifier.fillMaxWidth(),

        shape = MaterialTheme.shapes.extraLarge,

        tonalElevation = 6.dp

    ) {

        Box(

            modifier = Modifier
                .background(
                    Brush.horizontalGradient(
                        listOf(
                            XtoolsColors.BrandGreen,
                            XtoolsColors.FacebookBlue,
                            XtoolsColors.PurpleAccent
                        )
                    )
                )
                .padding(24.dp)

        ) {

            Column {

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
                            tint = XtoolsColors.BrandGreen,
                            modifier = Modifier.size(34.dp)
                        )

                    }

                    Spacer(Modifier.width(18.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            text = title,
                            style = MaterialTheme.typography.headlineSmall,
                            color = XtoolsColors.TextPrimary
                        )

                        Spacer(Modifier.height(4.dp))

                        Text(
                            text = subtitle,
                            style = MaterialTheme.typography.bodyMedium,
                            color = XtoolsColors.TextSecondary
                        )

                    }

                }

                Spacer(Modifier.height(18.dp))

                AssistChip(

                    onClick = {},

                    enabled = false,

                    label = {

                        Text(badge)

                    }

                )

            }

        }

    }

}
