package com.liyx.xtools.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.liyx.xtools.ui.components.ScriptAnalysisCard
import com.liyx.xtools.design.components.PrimaryButton
import com.liyx.xtools.design.components.TopBar
import com.liyx.xtools.ui.components.GenerationPipelineCard
import com.liyx.xtools.ui.components.GenerationDashboardCard
import com.liyx.xtools.ui.components.ChunkPreviewCard

import com.liyx.xtools.ui.components.ProjectTitleCard
import com.liyx.xtools.ui.components.QuickStatCard
import com.liyx.xtools.ui.components.ScriptEditorCard
import com.liyx.xtools.ui.components.VoiceHeader
import com.liyx.xtools.ui.components.VoiceSettingsCard
import com.liyx.xtools.viewmodel.VoiceViewModel
import com.liyx.xtools.ui.components.VoiceSelectorCard

import com.liyx.xtools.AppContainer
import com.liyx.xtools.viewmodel.VoiceViewModelFactory
import androidx.compose.material3.Text
import com.liyx.xtools.core.utils.DebugLogger

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.liyx.xtools.ui.components.GeneratedAudioCard

@Composable
fun VoiceStudioScreen(

    appContainer: AppContainer,

    onBack: () -> Unit,

    onOpenLibrary: () -> Unit

)

 {

    val context = LocalContext.current

    var debugLog by remember {
        mutableStateOf(DebugLogger.read(context))
    }

    val viewModel: VoiceViewModel = viewModel(
        factory = VoiceViewModelFactory(
            appContainer
        )
    )

    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopBar(
                title = "Voice Studio",
                subtitle = "Professional AI Voice Generator",
                showBack = true,
                onBack = onBack
            )
        }
    ) { padding ->

        LazyColumn(
            state = rememberLazyListState(),
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            item {
                VoiceHeader()
            }

            item {
                ProjectTitleCard(
                    title = state.title,
                    onTitleChanged = {
                        viewModel.updateTitle(it)
                    }
                )
            }

            item {
                ScriptEditorCard(
                    text = state.text,
                    characterCount = state.characterCount,
                    estimatedDuration = state.estimatedDurationMs,
                    onTextChanged = {
                        viewModel.updateText(it)
                    }
                )
            }

            item {
                ScriptAnalysisCard(
                    characterCount = state.characterCount,
                    estimatedDuration = state.estimatedDurationMs,
                    chunkCount = state.estimatedChunks,
                    wordCount = state.wordCount,
                    paragraphCount = state.paragraphCount
                )
            }

            item {
                VoiceSelectorCard(
                    selectedVoice = state.selectedVoice,
                    onVoiceSelected = {
                        viewModel.updateVoice(it)
                    }
                )
            }

            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    QuickStatCard(
                        modifier = Modifier.weight(1f),
                        value = state.characterCount.toString(),
                        label = "Characters"
                    )

                    QuickStatCard(
                        modifier = Modifier.weight(1f),
                        value = formatDuration(state.estimatedDurationMs),
                        label = "Estimated Audio"
                    )
                }
            }

            item {
                VoiceSettingsCard(
                    speed = state.speed,
                    pitch = state.pitch,
                    selectedVoice = state.selectedVoice,
                    onSpeedChanged = {
                        viewModel.updateSpeed(it)
                    },
                    onPitchChanged = {
                        viewModel.updatePitch(it)
                    }
                )
            }

            item {
                PrimaryButton(
                    text = if (state.isGenerating)
                        "Generating..."
                    else
                        "Generate Voice",
                    onClick = {
                        viewModel.generateVoice()
                    },
                    enabled = state.text.isNotBlank(),
                    loading = state.isGenerating
                )
            }

            item {
                GenerationPipelineCard(
                    generating = state.isGenerating,
                    progress = state.progress,
                    currentVoice = state.selectedVoice,
                    characterCount = state.characterCount,
                    estimatedDuration = state.estimatedDurationMs
                )
            }

            item {
                Text(
                    text = "Debug: ${state.debugMessage}",
                    modifier = Modifier.padding(8.dp)
                )
            }

            item {
                GenerationDashboardCard(
                    progress = state.progress,
                    processedChunks = 0,
                    totalChunks = state.estimatedChunks,
                    processedCharacters = 0,
                    totalCharacters = state.characterCount,
                    generating = state.isGenerating
                )
            }

item {

    GeneratedAudioCard(

        audioPath = state.generatedAudio,

        canPlay = state.canPlay,

        canShare = state.canShare,

        canExport = state.canExport,

        onPlay = {

            viewModel.playGeneratedAudio()

        },

        onPause = {

            viewModel.pauseAudio()

        },

        onStop = {

            viewModel.stopAudio()

        },

        onShare = {

            viewModel.shareGeneratedAudio()

        },

        onExport = {

            viewModel.exportGeneratedAudio()

        }

    )

}

item {

    Spacer(
        modifier = Modifier.height(16.dp)
    )

    Button(

        onClick = onOpenLibrary,

        modifier = Modifier.fillMaxWidth()

    ) {

        Text("📚 Open Audio Library")

    }

}

            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = "Developer Debug Console"
                        )

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Text(
                            text = debugLog
                        )

                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )

                        Row {
                            Button(
                                onClick = {
                                    debugLog = DebugLogger.read(context)
                                }
                            ) {
                                Text("Refresh")
                            }

                            Spacer(
                                modifier = Modifier.width(12.dp)
                            )

                            Button(
                                onClick = {
                                    val clipboard =
                                        context.getSystemService(
                                            android.content.Context.CLIPBOARD_SERVICE
                                        ) as android.content.ClipboardManager

                                    clipboard.setPrimaryClip(
                                        android.content.ClipData.newPlainText(
                                            "Xtools Debug",
                                            debugLog
                                        )
                                    )
                                }
                            ) {
                                Text("Copy Log")
                            }
                        }
                    }
                }
            }

            item {
                ChunkPreviewCard(
                    chunks = state.chunks
                )
            }

            item {
                Spacer(
                    modifier = Modifier.height(20.dp)
                )
            }
        }
    }
}

private fun formatDuration(
    duration: Long
): String {

    val totalSeconds = duration / 1000
    val hours = totalSeconds / 3600
    val minutes = (totalSeconds % 3600) / 60
    val seconds = totalSeconds % 60

    return when {
        hours > 0 -> "${hours}h ${minutes}m"
        minutes > 0 -> "${minutes}m ${seconds}s"
        else -> "${seconds}s"
    }
}

