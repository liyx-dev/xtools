package com.liyx.xtools.design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.HighQuality
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun VoiceCard(

    name: String,

    locale: String,

    provider: String,

    quality: String,

    offline: Boolean,

    selected: Boolean,

    favorite: Boolean,

    modifier: Modifier = Modifier,

    onClick: () -> Unit,

    onFavoriteClick: () -> Unit,

    onPreviewClick: () -> Unit

) {

    Card(

        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },

        colors = CardDefaults.cardColors(

            containerColor =
                if (selected)
                    MaterialTheme.colorScheme.primaryContainer
                else
                    MaterialTheme.colorScheme.surface

        ),

        elevation = CardDefaults.cardElevation(

            defaultElevation =
                if (selected) 10.dp else 3.dp

        ),

        shape = MaterialTheme.shapes.large

    ) {

        Column(

            modifier = Modifier.padding(18.dp)

        ) {

            Row(

                verticalAlignment = Alignment.CenterVertically

            ) {

                Box(

                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),

                    contentAlignment = Alignment.Center

                ) {

                    Icon(

                        imageVector = Icons.Default.GraphicEq,

                        contentDescription = null,

                        tint = Color.White

                    )

                }

                Spacer(Modifier.width(16.dp))

                Column(

                    modifier = Modifier.weight(1f)

                ) {

                    Text(

                        text = name,

                        style = MaterialTheme.typography.titleMedium

                    )

                    Spacer(Modifier.height(4.dp))

                    Text(

                        text = provider,

                        color = MaterialTheme.colorScheme.primary,

                        style = MaterialTheme.typography.labelMedium

                    )

                }

                Icon(

                    imageVector =
                        if (favorite)
                            Icons.Default.Favorite
                        else
                            Icons.Default.FavoriteBorder,

                    contentDescription = null,

                    tint =
                        if (favorite)
                            Color.Red
                        else
                            MaterialTheme.colorScheme.outline,

                    modifier = Modifier.clickable {

                        onFavoriteClick()

                    }

                )

            }

            Spacer(Modifier.height(16.dp))

            Row(

                horizontalArrangement = Arrangement.spacedBy(8.dp)

            ) {

                AssistChip(

                    onClick = {},

                    label = {

                        Text(locale)

                    },

                    leadingIcon = {

                        Icon(

                            Icons.Default.Language,

                            null

                        )

                    }

                )

                AssistChip(

                    onClick = {},

                    label = {

                        Text(quality)

                    },

                    leadingIcon = {

                        Icon(

                            Icons.Default.HighQuality,

                            null

                        )

                    }

                )

            }

            Spacer(Modifier.height(10.dp))

            Row(

                horizontalArrangement = Arrangement.spacedBy(8.dp)

            ) {

                AssistChip(

                    onClick = {},

                    label = {

                        Text(

                            if (offline)
                                "Offline"
                            else
                                "Online"

                        )

                    },

                    leadingIcon = {

                        Icon(

                            if (offline)
                                Icons.Default.Download
                            else
                                Icons.Default.Cloud,

                            null

                        )

                    },

                    colors = AssistChipDefaults.assistChipColors(

                        containerColor =
                            if (offline)
                                Color(0xFF1B5E20)
                            else
                                Color(0xFF1565C0)

                    )

                )

                if (selected) {

                    AssistChip(

                        onClick = {},

                        label = {

                            Text("Selected")

                        },

                        leadingIcon = {

                            Icon(

                                Icons.Default.CheckCircle,

                                null

                            )

                        }

                    )

                }

            }

            Spacer(Modifier.height(16.dp))

            SecondaryButton(

                text = "Preview Voice",

                onClick = onPreviewClick

            )

        }

    }

}
