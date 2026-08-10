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

import com.liyx.xtools.core.media.AudioMerger
import com.liyx.xtools.core.voice.VoiceEngine
import com.liyx.xtools.core.voice.VoicePipeline
import com.liyx.xtools.core.storage.AudioStorageManager
import com.liyx.xtools.core.player.AudioPlayer
import com.liyx.xtools.core.player.AudioLibraryManager
import com.liyx.xtools.core.player.models.AudioRecording
import java.util.UUID
import com.liyx.xtools.core.export.AndroidShareManager
import com.liyx.xtools.core.export.ExportAudio
import com.liyx.xtools.core.media.AudioExporter
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.withContext
import com.liyx.xtools.core.voice.VoiceConfig
import com.liyx.xtools.core.providers.VoiceProviderManager
import com.liyx.xtools.core.models.VoiceItem
import kotlinx.coroutines.delay
import com.liyx.xtools.core.providers.VoicePreference

class VoiceViewModel(

    private val voiceManager: com.liyx.xtools.core.voice.VoiceManager? = null,
private val providerManager: VoiceProviderManager,
private val voicePreference: VoicePreference,
    private val voiceEngine: VoiceEngine? = null,

   private val audioMerger: AudioMerger? = null, 
private val audioStorageManager: AudioStorageManager? = null,

private val audioPlayer: AudioPlayer? = null,
private val audioLibraryManager: AudioLibraryManager? = null,
private val audioExporter: AudioExporter? = null,
private var voiceConfig: VoiceConfig = VoiceConfig(),
private val androidShareManager: AndroidShareManager? = null
) : ViewModel()

 {

private val chunkEngine = SmartChunkEngine()


private val voicePipeline: VoicePipeline? =

    if (
        voiceEngine != null &&
        audioMerger != null
    ) {

        VoicePipeline(

            voiceEngine,

            audioMerger,

            { job ->

                _uiState.value =
                    _uiState.value.copy(

                        progress = job.progress,

                        currentChunk = job.completedChunks(),

                        totalChunks = job.totalChunks(),

                        processedCharacters = job.processedCharacters,

                        remainingCharacters =
                            job.totalCharacters -
                                job.processedCharacters

                    )

            }

        )

    } else {

        null

    }

    private val _uiState = MutableStateFlow(
        VoiceUiState()
    )

    val uiState: StateFlow<VoiceUiState> =
        _uiState.asStateFlow()

init {
    loadVoices()
}

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


fun updateVoice(voiceId: String) {

    providerManager.setSelectedVoice(voiceId)
voicePreference.saveVoice(voiceId)
    val selected = providerManager.getSelectedVoice()

  if (selected == null) {
    debug("Voice not found")
    return
}

voiceConfig = voiceConfig.copy(
    voiceId = selected.id
)

providerManager.getCurrentEngine()?.applyConfig(voiceConfig)

_uiState.value = _uiState.value.copy(
    selectedVoiceId = selected.id,
    selectedVoiceName = selected.name
)


}




    fun updateSpeed(

    speed: Float

) {

if (voiceConfig.speed == speed) return
    voiceConfig = voiceConfig.copy(

        speed = speed

    )

    

    providerManager.getCurrentEngine()?.applyConfig(
    voiceConfig
)

    _uiState.value = _uiState.value.copy(

        speed = speed

    )

}

    fun updatePitch(

    pitch: Float

) {
if (voiceConfig.pitch == pitch) return

    voiceConfig = voiceConfig.copy(

        pitch = pitch

    )

    providerManager.getCurrentEngine()?.applyConfig(
    voiceConfig
)

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
           
private fun loadVoices() {

    val voices = providerManager.getAllVoices()

    if (voices.isEmpty()) {

        viewModelScope.launch {

            delay(800)

            loadVoices()

        }

        return

    }

    val selected = providerManager.getSelectedVoice()

    if (selected == null) {

        providerManager.setSelectedVoice(voices.first().id)

        loadVoices()

        return

    }

    _uiState.value = _uiState.value.copy(

        availableVoices = voices.map {

            VoiceItem(

                id = it.id,

                name = it.name,

                locale = it.locale,

                provider = it.provider,

                quality = it.quality,

                offline = it.isOffline

            )

        },

        selectedVoiceId = selected.id,

        selectedVoiceName = selected.name,

        providerReady = true,

        providerStatus = "${voices.size} voices loaded"

    )
}


fun refreshSelectedVoice() {

    val selected = providerManager.getSelectedVoice()

    if (selected != null) {

        voiceConfig = voiceConfig.copy(
            voiceId = selected.id
        )

        voiceEngine?.applyConfig(voiceConfig)

        _uiState.value = _uiState.value.copy(
            selectedVoiceId = selected.id,
            selectedVoiceName = selected.name
        )
    }
}


fun generateVoice() {

    val current = _uiState.value

    if (current.text.isBlank()) {
        debug("Cannot generate: text is empty")
        return
    }

    if (_uiState.value.isGenerating) {
        debug("Generation already in progress")
        return
    }

    setGenerating(true)

    viewModelScope.launch {

        try {

            val result = withContext(Dispatchers.IO) {

                val job = voiceManager?.createJob(
                    title = current.title.ifBlank {
                        "Untitled Project"
                    },
                    rawText = current.text
                )

                if (job == null) {
                    debug("Failed to create VoiceJob")
                    return@withContext null
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
                    audioStorageManager?.getOutputDirectory(job.title)

                if (outputDirectory == null) {
                    debug("Output directory unavailable")
                    return@withContext null
                }

                val currentEngine =
                    providerManager.getCurrentEngine()

                if (currentEngine == null) {
                    debug("No active voice engine available")
                    return@withContext null
                }

                debug(
                    "Using voice provider: " +
                        providerManager.getCurrentProviderId()
                )

                debug(
                    "Using voice: " +
                        providerManager.getSelectedVoiceName()
                )

                currentEngine.applyConfig(voiceConfig)

                val pipeline = VoicePipeline(

                    voiceEngine = currentEngine,

                    audioMerger =
                        audioMerger
                            ?: return@withContext null,

                    onChunkCompleted = { updatedJob ->

                        _uiState.value =
                            _uiState.value.copy(

                                progress =
                                    updatedJob.progress,

                                currentChunk =
                                    updatedJob.completedChunks(),

                                totalChunks =
                                    updatedJob.totalChunks(),

                                processedCharacters =
                                    updatedJob.processedCharacters,

                                remainingCharacters =
                                    updatedJob.totalCharacters -
                                        updatedJob.processedCharacters

                            )
                    },

                    logger = { message ->
                        debug(message)
                    }

                )

                val output = pipeline.process(
                    job,
                    outputDirectory
                )

                Pair(job, output)
            }

            if (result == null) {

                debug("Voice generation failed")

                return@launch
            }

            val (job, output) = result

            if (output == null) {

                debug("Voice pipeline returned no audio")

                return@launch
            }

            val outputFile = java.io.File(output)

            if (!outputFile.exists() ||
                outputFile.length() <= 0L
            ) {

                debug(
                    "Generated audio file is missing or empty"
                )

                return@launch
            }

            _uiState.value = _uiState.value.copy(

                generatedAudio = output,

                canPlay = true,

                canShare = true,

                canExport = true,

                progress = 1f,

                currentChunk =
                    job.totalChunks(),

                processedCharacters =
                    job.totalCharacters,

                remainingCharacters = 0

            )

            audioLibraryManager?.add(

                AudioRecording(

                    id =
                        UUID.randomUUID().toString(),

                    title =
                        job.title,

                    filePath =
                        output,

                    duration =
                        current.estimatedDurationMs,

                    createdAt =
                        System.currentTimeMillis()

                )

            )

            debug(
                "VOICE GENERATION COMPLETED"
            )

            debug(
                "Audio file: $output"
            )

            debug(
                "Audio size: ${outputFile.length()} bytes"
            )

        } catch (e: Exception) {

            debug(
                "Voice generation error:\n" +
                    e.stackTraceToString()
            )

        } finally {

            setGenerating(false)

        }
    }
}


fun playGeneratedAudio() {

    val file = _uiState.value.generatedAudio ?: return

    audioPlayer?.play(file)

    _uiState.value = _uiState.value.copy(
        isPlaying = true
    )
}

fun pauseAudio() {

    audioPlayer?.pause()

    _uiState.value = _uiState.value.copy(
        isPlaying = false
    )
}

fun resumeAudio() {

    audioPlayer?.resume()

    _uiState.value = _uiState.value.copy(
        isPlaying = true
    )
}

fun stopAudio() {

    audioPlayer?.stop()

    _uiState.value = _uiState.value.copy(
        isPlaying = false
    )
}

fun shareGeneratedAudio() {

    val path = _uiState.value.generatedAudio ?: return

    androidShareManager?.share(

       ExportAudio(

    title = _uiState.value.currentJobTitle.ifBlank {

        "Generated Audio"

    },

    filePath = path,

    mimeType = "audio/wav"

)

    )

}


fun exportGeneratedAudio() {

    val file = _uiState.value.generatedAudio ?: return

    val title =

        _uiState.value.currentJobTitle.ifBlank {

            "Generated Audio"

        }

    val success =

        audioExporter?.export(

            file,

            title

        ) ?: false

    debug(

        if (success)

            "Audio exported successfully."

        else

            "Audio export failed."

    )

}


fun canPlayAudio(): Boolean {

    return _uiState.value.generatedAudio != null

}

fun hasGeneratedAudio(): Boolean {

    return _uiState.value.generatedAudio != null

}

fun previewVoice(id: String) {

    debug("Preview voice: $id")

}

fun toggleFavorite(id: String) {

    val current = _uiState.value.favoriteVoices

    val updated =
        if (current.contains(id))
            current - id
        else
            current + id

    _uiState.value =
        _uiState.value.copy(
            favoriteVoices = updated
        )

}

 

}
