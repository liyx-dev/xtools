package com.liyx.xtools.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onOpenVoiceStudio: () -> Unit
) {

    Box(

        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF0F172A),
                        Color(0xFF111827),
                        Color.Black
                    )
                )
            )

    ) {

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)

        ) {

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                "Xtools",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White
            )

            Text(
                "AI Productivity Suite",
                color = Color.LightGray
            )

            Spacer(modifier = Modifier.height(40.dp))

            Card(

                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {

                        onOpenVoiceStudio()

                    },

                shape = RoundedCornerShape(24.dp)

            ) {

                Column(

                    modifier = Modifier.padding(24.dp)

                ) {

                    Icon(

                        Icons.Default.GraphicEq,

                        contentDescription = null,

                        tint = MaterialTheme.colorScheme.primary

                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(

                        "Voice Studio",

                        style = MaterialTheme.typography.titleLarge

                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(

                        "Generate realistic voices from text."

                    )

                }

            }

            Spacer(modifier = Modifier.height(36.dp))

            Text(

                "Coming Soon",

                color = Color.White,

                style = MaterialTheme.typography.titleMedium

            )

            Spacer(modifier = Modifier.height(16.dp))

            ComingSoonCard("AI Writer")

            ComingSoonCard("PDF AI")

            ComingSoonCard("Video AI")

            ComingSoonCard("Translator")

        }

    }

}

@Composable
private fun ComingSoonCard(
    title: String
) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)

    ) {

        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

            verticalAlignment = Alignment.CenterVertically

        ) {

            Icon(

                Icons.Default.Lock,

                contentDescription = null

            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(title)

        }

    }

}
