package com.liyx.xtools.ui.library

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.liyx.xtools.AppContainer
import com.liyx.xtools.design.components.TopBar
import com.liyx.xtools.ui.components.VoiceSelectorCard
import com.liyx.xtools.viewmodel.VoiceViewModel
import com.liyx.xtools.viewmodel.VoiceViewModelFactory

@Composable
fun VoiceLibraryScreen(

    appContainer: AppContainer,

    onBack: () -> Unit,
onOpenModelStore: () -> Unit

) {

    val viewModel: VoiceViewModel = viewModel(
        factory = VoiceViewModelFactory(appContainer)
    )

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

    Button(

        onClick = onOpenModelStore,

        modifier = Modifier.fillMaxWidth()

    ) {

        Text("🛍 Piper Model Store")

    }

}

            item {

                Text(

                    text = "Browse Voices",

                    style = MaterialTheme.typography.headlineSmall

                )

            }

            item {

                VoiceSelectorCard(

                    voices = state.availableVoices,

                    selectedVoice = state.selectedVoiceId,

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
