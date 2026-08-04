package com.liyx.xtools.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.liyx.xtools.design.XtoolsColors

@Composable
fun HeroSection() {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {

        Box(
            modifier = Modifier
                .background(
                    Brush.verticalGradient(
                        listOf(
                            XtoolsColors.BrandGreen,
                            XtoolsColors.FacebookBlue,
                            XtoolsColors.BrandGreenDark
                        )
                    )
                )
                .padding(24.dp)
        ) {

            Column {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Surface(
                        modifier = Modifier.size(92.dp),
                        shape = CircleShape,
                        color = Color.White.copy(alpha = .15f)
                    ) {

                        Box(
                            contentAlignment = Alignment.Center
                        ) {

                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(44.dp)
                            )

                        }

                    }

                    Spacer(
                        modifier = Modifier.width(18.dp)
                    )

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            text = "Xtools AI Studio",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                        Spacer(
                            modifier = Modifier.height(6.dp)
                        )

                        Text(
                            text = "Professional AI Creation Suite",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White.copy(alpha = .92f)
                        )

                    }

                }

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                Surface(
                    shape = RoundedCornerShape(50.dp),
                    color = Color.White.copy(alpha = .18f)
                ) {

                    Text(
                        text = "ANDROID TTS READY",
                        modifier = Modifier.padding(
                            horizontal = 18.dp,
                            vertical = 8.dp
                        ),
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                }

                Spacer(
                    modifier = Modifier.height(26.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    HeroChip(
                        icon = Icons.Default.GraphicEq,
                        title = "Voice"
                    )

                    HeroChip(
                        icon = Icons.Default.Edit,
                        title = "Writing"
                    )

                    HeroChip(
                        icon = Icons.Default.Share,
                        title = "Export"
                    )

                }

            }

        }

    }

}

@Composable
private fun HeroChip(

    icon: androidx.compose.ui.graphics.vector.ImageVector,

    title: String

) {

    Surface(
        color = Color.White.copy(alpha = .16f),
        shape = RoundedCornerShape(16.dp)
    ) {

        Row(
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 10.dp
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(18.dp)
            )

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Text(
                text = title,
                color = Color.White,
                style = MaterialTheme.typography.labelLarge
            )

        }

    }

}
