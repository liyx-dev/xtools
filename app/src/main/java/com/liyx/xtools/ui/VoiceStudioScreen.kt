package com.liyx.xtools.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
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

import com.liyx.xtools.ui.components.ProjectTitleCard
import com.liyx.xtools.ui.components.QuickStatCard
import com.liyx.xtools.ui.components.ScriptEditorCard
import com.liyx.xtools.ui.components.VoiceHeader
import com.liyx.xtools.ui.components.VoiceSettingsCard
import com.liyx.xtools.viewmodel.VoiceViewModel
import com.liyx.xtools.ui.components.VoiceSelectorCard

@Composable
fun VoiceStudioScreen(

    onBack: () -> Unit,

    viewModel: VoiceViewModel = viewModel()

) {

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

        hours > 0 ->
            "${hours}h ${minutes}m"

        minutes > 0 ->
            "${minutes}m ${seconds}s"

        else ->
            "${seconds}s"

    }

}
