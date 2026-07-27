package com.liyx.xtools.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class VoiceViewModel(

    private val voiceManager: com.liyx.xtools.core.voice.VoiceManager? = null

) : ViewModel()
 {

    private val _uiState = MutableStateFlow(
        VoiceUiState()
    )

    val uiState: StateFlow<VoiceUiState> =
        _uiState.asStateFlow()

    fun updateTitle(title: String) {

        _uiState.value = _uiState.value.copy(
            title = title
        )

    }

    fun updateText(text: String) {

        val duration = estimateDuration(text)

        _uiState.value = _uiState.value.copy(

            text = text,

            estimatedDurationMs = duration

        )

    }

    fun updateVoice(voice: String) {

        _uiState.value = _uiState.value.copy(

            selectedVoice = voice

        )

    }

    fun updateSpeed(speed: Float) {

        _uiState.value = _uiState.value.copy(

            speed = speed

        )

    }

    fun updatePitch(pitch: Float) {

        _uiState.value = _uiState.value.copy(

            pitch = pitch

        )

    }

    fun setGenerating(generating: Boolean) {

        _uiState.value = _uiState.value.copy(

            isGenerating = generating

        )

    }

    fun updateProgress(progress: Float) {

        _uiState.value = _uiState.value.copy(

            progress = progress

        )

    }

    private fun estimateDuration(

        text: String

    ): Long {

        if (text.isBlank()) return 0L

        val words = text.trim()

            .split(Regex("\\s+"))

            .size

        val wordsPerMinute = 160

        val minutes = words.toDouble() / wordsPerMinute

        return (minutes * 60_000).toLong()

    }
fun generateVoice() {

    val current = _uiState.value

    if (current.text.isBlank()) return

    setGenerating(true)

    voiceManager?.createJob(

        title = current.title.ifBlank {

            "Untitled Project"

        },

        rawText = current.text

    )

}

}
