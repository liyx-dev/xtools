package com.liyx.xtools.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.liyx.xtools.ui.components.ComingSoonCard
import com.liyx.xtools.ui.components.FeatureCard
import com.liyx.xtools.ui.components.HeroSection
import com.liyx.xtools.ui.components.QuickStatCard

@Composable
fun HomeScreen(
    onOpenVoiceStudio: () -> Unit
) {

    val upcomingFeatures = listOf(
        "AI Writer",
        "PDF AI"
    )

    Scaffold { innerPadding ->

        LazyColumn(

            modifier = Modifier.fillMaxSize(),

            contentPadding = PaddingValues(
                start = 20.dp,
                end = 20.dp,
                top = innerPadding.calculateTopPadding() + 20.dp,
                bottom = 32.dp
            ),

            verticalArrangement = Arrangement.spacedBy(20.dp)

        ) {

            item {

                HeroSection()

            }

            item {

                FeatureCard(

                    title = "Voice Studio",

                    subtitle = "Generate natural AI voices from text in seconds.",

                    icon = Icons.Default.GraphicEq,

                    onClick = onOpenVoiceStudio

                )

            }

            item {

                Row(

                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement = Arrangement.spacedBy(16.dp)

                ) {

                    QuickStatCard(

                        modifier = Modifier.weight(1f),

                        value = "10+",

                        label = "AI Tools"

                    )

                    QuickStatCard(

                        modifier = Modifier.weight(1f),

                        value = "100K",

                        label = "Characters"

                    )

                }

            }

            item {

                Spacer(modifier = Modifier.height(4.dp))

                Text(

                    text = "Available Soon",

                    style = MaterialTheme.typography.titleLarge

                )

            }

            items(upcomingFeatures) { feature ->

                ComingSoonCard(

                    title = feature

                )

            }

        }

    }

}
