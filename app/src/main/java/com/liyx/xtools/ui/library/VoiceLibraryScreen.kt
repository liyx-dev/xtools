package com.liyx.xtools.ui.library

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.liyx.xtools.viewmodel.VoiceViewModel
import com.liyx.xtools.design.components.TopBar
import com.liyx.xtools.ui.components.VoiceSelectorCard


@Composable
fun VoiceLibraryScreen(

    viewModel: VoiceViewModel,

    onBack: () -> Unit

)

 {

    val state by viewModel.uiState.collectAsState()

    Scaffold(

        topBar = {

            TopBar(

                title = "Voice Library",

                subtitle = "Choose your perfect narration voice",

                showBack = true,

                onBack = onBack

            )

        }

    ) { padding ->

        LazyColumn(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding),

            contentPadding = PaddingValues(20.dp),

            verticalArrangement = Arrangement.spacedBy(20.dp)

        ) {

            item {

                Text(

                    text = "Browse Voices",

                    style = MaterialTheme.typography.headlineSmall

                )

            }

            item {

                VoiceSelectorCard(

                    voices = state.availableVoices,

                    selectedVoice = state.selectedVoice,

                    favorites = state.favoriteVoices,

                    onVoiceSelected = {

                        viewModel.updateVoice(it)

                    },

                    onPreview = {

                        viewModel.previewVoice(it)

                    },

                    onFavorite = {

                        viewModel.toggleFavorite(it)

                    }

                )

            }

        }

    }

}
