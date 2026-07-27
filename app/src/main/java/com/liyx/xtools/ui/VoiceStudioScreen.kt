package com.liyx.xtools.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.liyx.xtools.viewmodel.VoiceViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VoiceStudioScreen(

    onBack: () -> Unit,

    viewModel: VoiceViewModel = viewModel()

) {

    val state by viewModel.uiState.collectAsState()

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text("Voice Studio")

                }

            )

        }

    ) { padding ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp)
                .verticalScroll(rememberScrollState())

        ) {

            OutlinedTextField(

                value = state.title,

                onValueChange = {

                    viewModel.updateTitle(it)

                },

                label = {

                    Text("Project Title")

                },

                modifier = Modifier.fillMaxWidth()

            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(

                value = state.text,

                onValueChange = {

                    viewModel.updateText(it)

                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),

                label = {

                    Text("Enter text")

                }

            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                "Characters: ${state.characterCount}"
            )

            Text(
                "Estimated Duration: ${state.estimatedDurationMs / 1000}s"
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text("Speech Speed")

            Slider(

                value = state.speed,

                onValueChange = {

                    viewModel.updateSpeed(it)

                },

                valueRange = 0.5f..2f

            )

            Spacer(modifier = Modifier.height(12.dp))

            Text("Pitch")

            Slider(

                value = state.pitch,

                onValueChange = {

                    viewModel.updatePitch(it)

                },

                valueRange = 0.5f..2f

            )

            Spacer(modifier = Modifier.height(24.dp))

          Button(

    onClick = {

        viewModel.generateVoice()

                },

                modifier = Modifier.fillMaxWidth()

            ) {

                Text("Generate Voice")

            }

            Spacer(modifier = Modifier.height(16.dp))

            if (state.isGenerating) {

                LinearProgressIndicator(

                    progress = { state.progress },

                    modifier = Modifier.fillMaxWidth()

                )

            }

        }

    }

}
