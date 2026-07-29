package com.liyx.xtools.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.liyx.xtools.core.voice.SmartChunkEngine
import com.liyx.xtools.core.queue.VoiceJobQueue

import com.liyx.xtools.core.jobs.VoiceJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VoiceViewModel(

    private val voiceManager: com.liyx.xtools.core.voice.VoiceManager? = null

) : ViewModel()
 {

private val chunkEngine = SmartChunkEngine()
private val voiceJobQueue = VoiceJobQueue()
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

    val words = if (text.isBlank()) {
        0
    } else {
        text.trim().split(Regex("\\s+")).size
    }

    val paragraphs = if (text.isBlank()) {
        0
    } else {
        text.trim().split(Regex("\\n\\s*\\n")).size
    }

    val chunks = chunkEngine.split(text)

    _uiState.value = _uiState.value.copy(

        text = text,

        estimatedDurationMs = duration,

        wordCount = words,

        paragraphCount = paragraphs,

        estimatedChunks = chunks.size,

        chunks = chunks

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

private fun updateQueueSize() {

    _uiState.value = _uiState.value.copy(
        queueSize = voiceJobQueue.size()
    )

}

private fun processNextJob() {

    if (voiceJobQueue.isProcessing()) return

    val job = voiceJobQueue.peek() ?: return

    voiceJobQueue.startProcessing()

    CoroutineScope(Dispatchers.IO).launch {

        setGenerating(true)

        /*
         * Phase 7B:
         * Actual VoicePipeline processing
         * will be connected here.
         */

        updateProgress(1f)

        voiceJobQueue.dequeue()

        voiceJobQueue.finishProcessing()

        updateQueueSize()

        setGenerating(false)

        processNextJob()

    }

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



private fun countWords(

    text: String

): Int {

    if (text.isBlank()) return 0

    return text

        .trim()

        .split(Regex("\\s+"))

        .size

}

private fun countParagraphs(

    text: String

): Int {

    if (text.isBlank()) return 0

    return text

        .trim()

        .split(Regex("\\n+"))

        .count {

            it.isNotBlank()

        }

}

private fun estimateChunks(

    text: String

): Int {

    if (text.isBlank()) return 0

    return chunkEngine
        .split(text)
        .size

}


fun generateVoice() {

    val current = _uiState.value

    if (current.text.isBlank()) return

    val job = voiceManager?.createJob(

        title = current.title.ifBlank {

            "Untitled Project"

        },

        rawText = current.text

    )

    if (job != null) {

        voiceJobQueue.enqueue(job)

updateQueueSize()

processNextJob()

    }

}

 

}
