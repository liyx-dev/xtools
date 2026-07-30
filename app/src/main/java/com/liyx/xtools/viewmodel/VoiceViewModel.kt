package com.liyx.xtools.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.liyx.xtools.core.voice.SmartChunkEngine


import com.liyx.xtools.core.jobs.VoiceJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import com.liyx.xtools.core.voice.AudioMerger
import com.liyx.xtools.core.voice.VoiceEngine
import com.liyx.xtools.core.voice.VoicePipeline
import com.liyx.xtools.core.storage.AudioStorageManager

class VoiceViewModel(

    private val voiceManager: com.liyx.xtools.core.voice.VoiceManager? = null,

    private val voiceEngine: VoiceEngine? = null,

   private val audioMerger: AudioMerger? = null, 

private val audioStorageManager: AudioStorageManager? = null

) : ViewModel()

 {

private val chunkEngine = SmartChunkEngine()


private val voicePipeline =

    if (

        voiceEngine != null &&

        audioMerger != null

    ) {


        VoicePipeline(

    voiceEngine,

    audioMerger,

    { job ->

        _uiState.value = _uiState.value.copy(

            progress = job.progress,

            currentChunk = job.completedChunks(),

            totalChunks = job.totalChunks(),

            processedCharacters = job.processedCharacters,

            remainingCharacters =
                job.totalCharacters - job.processedCharacters

        )

    },

    { message ->

        debug(message)

    }

)

    

    private val _uiState = MutableStateFlow(
        VoiceUiState()
    )

    val uiState: StateFlow<VoiceUiState> =
        _uiState.asStateFlow()

private fun debug(message: String) {

    _uiState.value = _uiState.value.copy(

        debugMessage = message

    )

}

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

    setGenerating(true)

    CoroutineScope(Dispatchers.IO).launch {

        val job = voiceManager?.createJob(

            title = current.title.ifBlank {
                "Untitled Project"
            },

            rawText = current.text

        )

        if (job == null) {

            setGenerating(false)

            return@launch

        }

        _uiState.value = _uiState.value.copy(

            currentJobTitle = job.title,

            totalChunks = job.totalChunks(),

            currentChunk = 0,

            processedCharacters = 0,

            remainingCharacters = job.totalCharacters,

            progress = 0f

        )

        val outputDirectory =

    audioStorageManager?.getOutputDirectory(
        job.title
    )

if (outputDirectory == null) {

    setGenerating(false)

    return@launch

}

val result =

    voicePipeline?.process(

        job,

        outputDirectory

    )

        if (result != null) {

            _uiState.value = _uiState.value.copy(

                generatedAudio = result,

                canPlay = true,

                canShare = true,

                canExport = true,

                progress = 1f,

                currentChunk = job.totalChunks(),

                processedCharacters = job.totalCharacters,

                remainingCharacters = 0

            )

        }

        setGenerating(false)

    }

}

     

 

}
