package com.liyx.xtools.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.liyx.xtools.design.components.VoiceCard
import com.liyx.xtools.design.components.XTextField

data class VoiceItem(

    val id: String,

    val name: String,

    val locale: String,

    val provider: String,

    val quality: String,

    val offline: Boolean

)

@Composable
fun VoiceSelectorCard(

    voices: List<VoiceItem>,

    selectedVoice: String,

    favorites: Set<String> = emptySet(),

    modifier: Modifier = Modifier,

    onVoiceSelected: (String) -> Unit,

    onPreview: (String) -> Unit,

    onFavorite: (String) -> Unit

) {

    var search by remember {

        mutableStateOf("")

    }

    val filteredVoices = remember(

        search,

        voices

    ) {

        if (search.isBlank())

            voices

        else

            voices.filter {

                it.name.contains(

                    search,

                    ignoreCase = true

                ) ||

                it.locale.contains(

                    search,

                    ignoreCase = true

                ) ||

                it.provider.contains(

                    search,

                    ignoreCase = true

                )

            }

    }

    Card(

        modifier = modifier.fillMaxWidth(),

        shape = MaterialTheme.shapes.extraLarge

    ) {

        Column(

            modifier = Modifier.padding(20.dp)

        ) {

            Text(

                text = "Voice Library",

                style = MaterialTheme.typography.titleLarge

            )

            Spacer(

                Modifier.height(4.dp)

            )

            Text(

                text = "${filteredVoices.size} voices available",

                style = MaterialTheme.typography.bodySmall,

                color = MaterialTheme.colorScheme.outline

            )

            Spacer(

                Modifier.height(16.dp)

            )

            XTextField(

                value = search,

                onValueChange = {

                    search = it

                },

                label = "Search voices",

                leadingIcon = Icons.Default.Search

            )

            Spacer(

                Modifier.height(20.dp)

            )

            LazyColumn(

                verticalArrangement = Arrangement.spacedBy(14.dp),

                modifier = Modifier.heightIn(max = 420.dp)

            ) {

                items(

                    filteredVoices,

                    key = { it.id }

                ) { voice ->

                    VoiceCard(

                        name = voice.name,

                        locale = voice.locale,

                        provider = voice.provider,

                        quality = voice.quality,

                        offline = voice.offline,

                        selected =

                            voice.id == selectedVoice,

                        favorite =

                            favorites.contains(voice.id),

                        onClick = {

                            onVoiceSelected(

                                voice.id

                            )

                        },

                        onPreviewClick = {

                            onPreview(

                                voice.id

                            )

                        },

                        onFavoriteClick = {

                            onFavorite(

                                voice.id

                            )

                        }

                    )

                }

            }

        }

    }

}
       
