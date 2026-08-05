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
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import com.liyx.xtools.design.components.SecondaryButton


@Composable
fun VoiceStudioScreen(

    appContainer: AppContainer,

    onBack: () -> Unit,

    onOpenLibrary: () -> Unit,
onOpenVoiceLibrary: () -> Unit

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
LaunchedEffect(Unit) {
    viewModel.refreshSelectedVoice()
}

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

    VoiceHeader(

        title = "Xtools Voice Studio",

        subtitle = "Create studio-quality narration with AI voices",

        badge = "ANDROID TTS READY"

    )

}

item {

    ElevatedCard(

        modifier = Modifier.fillMaxWidth(),

        shape = MaterialTheme.shapes.extraLarge

    ) {

        Column(

            modifier = Modifier.padding(20.dp)

        ) {

            Text(

                "🎬 Voice Project",

                style = MaterialTheme.typography.titleLarge,

                color = MaterialTheme.colorScheme.primary

            )

            Spacer(Modifier.height(4.dp))

            Text(

                "Every great narration starts with a project.",

                style = MaterialTheme.typography.bodyMedium,

                color = MaterialTheme.colorScheme.onSurfaceVariant

            )

            Spacer(Modifier.height(18.dp))

            ProjectTitleCard(

                title = state.title,

                onTitleChanged = {

                    viewModel.updateTitle(it)

                }

            )

        }

    }

}

item {

    ElevatedCard(

        modifier = Modifier.fillMaxWidth(),

        shape = MaterialTheme.shapes.extraLarge

    ) {

        Column(

            modifier = Modifier.padding(20.dp)

        ) {

            Text(

                "📝 Script Studio",

                style = MaterialTheme.typography.titleLarge,

                color = MaterialTheme.colorScheme.primary

            )

            Spacer(Modifier.height(4.dp))

            Text(

                "Write, paste or edit your narration script.",

                style = MaterialTheme.typography.bodyMedium,

                color = MaterialTheme.colorScheme.onSurfaceVariant

            )

            Spacer(Modifier.height(16.dp))

            ScriptEditorCard(

                text = state.text,

                characterCount = state.characterCount,

                estimatedDuration = state.estimatedDurationMs,

                onTextChanged = {

                    viewModel.updateText(it)

                }

            )

        }

    }

}

    item {

    ElevatedCard(

        modifier = Modifier.fillMaxWidth(),

        shape = MaterialTheme.shapes.extraLarge

    ) {

        Column(

            modifier = Modifier.padding(20.dp)

        ) {

            Text(

                text = "📊 Live Script Analytics",

                style = MaterialTheme.typography.titleLarge,

                color = MaterialTheme.colorScheme.primary

            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            ScriptAnalysisCard(

                characterCount = state.characterCount,

                estimatedDuration = state.estimatedDurationMs,

                chunkCount = state.estimatedChunks,

                wordCount = state.wordCount,

                paragraphCount = state.paragraphCount

            )

        }

    }

}

item {

    Row(

        modifier = Modifier.fillMaxWidth(),

        horizontalArrangement = Arrangement.spacedBy(14.dp)

    ) {

        QuickStatCard(

            modifier = Modifier.weight(1f),

            value = state.characterCount.toString(),

            label = "Characters",

            icon = "✍"

        )

        QuickStatCard(

            modifier = Modifier.weight(1f),

            value = state.wordCount.toString(),

            label = "Words",

            icon = "📖"

        )

    }

}

item {

    Row(

        modifier = Modifier.fillMaxWidth(),

        horizontalArrangement = Arrangement.spacedBy(14.dp)

    ) {

        QuickStatCard(

            modifier = Modifier.weight(1f),

            value = state.estimatedChunks.toString(),

            label = "Chunks",

            icon = "🧩"

        )

        QuickStatCard(

            modifier = Modifier.weight(1f),

            value = formatDuration(state.estimatedDurationMs),

            label = "Duration",

            icon = "⏱"

        )

    }

}

item {

    ElevatedCard(

        modifier = Modifier.fillMaxWidth(),

        shape = MaterialTheme.shapes.extraLarge

    ) {

        Column(

            modifier = Modifier.padding(22.dp)

        ) {

            Text(

                text = "🎙 Selected Voice",

                style = MaterialTheme.typography.titleLarge,

                color = MaterialTheme.colorScheme.primary

            )

            Spacer(

                modifier = Modifier.height(8.dp)

            )

            Text(

                text =state.selectedVoiceName,

                style = MaterialTheme.typography.titleMedium

            )

            Spacer(

                modifier = Modifier.height(4.dp)

            )

            Text(

                text = "Tap below to browse the Voice Library.",

                style = MaterialTheme.typography.bodyMedium,

                color = MaterialTheme.colorScheme.onSurfaceVariant

            )

            Spacer(

                modifier = Modifier.height(20.dp)

            )

            PrimaryButton(

                text = "🎙 Browse Voice Library",

                onClick = {

                    onOpenVoiceLibrary()

                }

            )

        }

    }

}

       
item {

    ElevatedCard(

        modifier = Modifier.fillMaxWidth(),

        shape = MaterialTheme.shapes.extraLarge

    ) {

        Column(

            modifier = Modifier.padding(22.dp)

        ) {

            Text(

                text = "🎛 Studio Control Center",

                style = MaterialTheme.typography.titleLarge,

                color = MaterialTheme.colorScheme.primary

            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(

                text = "Fine tune your narration before generating audio.",

                style = MaterialTheme.typography.bodyMedium,

                color = MaterialTheme.colorScheme.onSurfaceVariant

            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            VoiceSettingsCard(

                speed = state.speed,

                pitch = state.pitch,

               selectedVoice = state.selectedVoiceName,

                onSpeedChanged = {

                    viewModel.updateSpeed(it)

                },

                onPitchChanged = {

                    viewModel.updatePitch(it)

                }

            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            SecondaryButton(

                text = "🎧 Preview Voice",

                onClick = {

                    // Preview coming next phase

                }

            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            PrimaryButton(

                text =

                    if (state.isGenerating)

                        "Generating Premium Voice..."

                    else

                        "🚀 Generate Voice",

                loading = state.isGenerating,

                enabled = state.text.isNotBlank(),

                onClick = {

                    viewModel.generateVoice()

                }

            )

        }

    }

}

       item {

    ElevatedCard(

        modifier = Modifier.fillMaxWidth(),

        shape = MaterialTheme.shapes.extraLarge

    ) {

        Column(

            modifier = Modifier.padding(22.dp)

        ) {

            Text(

                text = "⚙ Production Dashboard",

                style = MaterialTheme.typography.titleLarge,

                color = MaterialTheme.colorScheme.primary

            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(

                text = "Real-time voice rendering progress",

                style = MaterialTheme.typography.bodyMedium,

                color = MaterialTheme.colorScheme.onSurfaceVariant

            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            GenerationPipelineCard(

                generating = state.isGenerating,

                progress = state.progress,

                currentVoice = state.selectedVoiceName,

                characterCount = state.characterCount,

                estimatedDuration = state.estimatedDurationMs

            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            GenerationDashboardCard(

                progress = state.progress,

                processedChunks = state.currentChunk,

                totalChunks = state.totalChunks,

                processedCharacters = state.processedCharacters,

                totalCharacters = state.characterCount,

                generating = state.isGenerating

            )

        }

    }

}
        
    
            item {

    ElevatedCard(

        modifier = Modifier.fillMaxWidth(),

        shape = MaterialTheme.shapes.extraLarge

    ) {

        Column(

            modifier = Modifier.padding(22.dp)

        ) {

            Text(

                text = "🎧 Studio Player",

                style = MaterialTheme.typography.titleLarge,

                color = MaterialTheme.colorScheme.primary

            )

            Spacer(Modifier.height(6.dp))

            Text(

                text = "Preview, export and share your generated narration.",

                style = MaterialTheme.typography.bodyMedium,

                color = MaterialTheme.colorScheme.onSurfaceVariant

            )

            Spacer(Modifier.height(18.dp))

            GeneratedAudioCard(

                audioPath = state.generatedAudio,

                canPlay = state.canPlay,

                canShare = state.canShare,

                canExport = state.canExport,

                onPlay = { viewModel.playGeneratedAudio() },

                onPause = { viewModel.pauseAudio() },

                onStop = { viewModel.stopAudio() },

                onShare = { viewModel.shareGeneratedAudio() },

                onExport = { viewModel.exportGeneratedAudio() }

            )

        }

    }

}
    


item {

    ElevatedCard(

        modifier = Modifier.fillMaxWidth(),

        shape = MaterialTheme.shapes.extraLarge

    ) {

        Column(

            modifier = Modifier.padding(22.dp)

        ) {

            Text(

                text = "🎵 Audio Library",

                style = MaterialTheme.typography.titleLarge,

                color = MaterialTheme.colorScheme.primary

            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(

                text = "Manage every recording you've created.",

                style = MaterialTheme.typography.bodyMedium,

                color = MaterialTheme.colorScheme.onSurfaceVariant

            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            PrimaryButton(

                text = "📚 Open Audio Library",

                onClick = onOpenLibrary

            )

        }

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

    text = "Developer Console",

    style = MaterialTheme.typography.titleLarge,

    color = MaterialTheme.colorScheme.primary

)

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                     Text(

    text = debugLog,

    style = MaterialTheme.typography.bodySmall,

    color = MaterialTheme.colorScheme.onSurfaceVariant

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

    ElevatedCard(

        modifier = Modifier.fillMaxWidth(),

        shape = MaterialTheme.shapes.extraLarge

    ) {

        Column(

            modifier = Modifier.padding(22.dp)

        ) {

            Text(

                text = "🧩 Smart Chunk Preview",

                style = MaterialTheme.typography.titleLarge,

                color = MaterialTheme.colorScheme.primary

            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(

                text = "Preview how your script will be divided before synthesis.",

                style = MaterialTheme.typography.bodyMedium,

                color = MaterialTheme.colorScheme.onSurfaceVariant

            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            ChunkPreviewCard(

                chunks = state.chunks

            )

        }

    }

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

