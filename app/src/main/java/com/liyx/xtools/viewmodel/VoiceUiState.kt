package com.liyx.xtools.viewmodel

import com.liyx.xtools.core.models.Chunk
import com.liyx.xtools.core.models.VoiceItem

data class VoiceUiState(

    val title: String = "",

    val text: String = "",

    // ===== Provider =====

    val selectedProvider: String = "android",

    val availableProviders: List<String> = emptyList(),

    // ===== Voice =====

    val selectedVoiceId: String = "",

val selectedVoiceName: String = "No Voice Selected",

val availableVoices: List<VoiceItem> = emptyList(),

val favoriteVoices: Set<String> = emptySet(),

    // ===== Voice Controls =====

    val speed: Float = 1f,

    val pitch: Float = 1f,

    // ===== Generation =====

    val progress: Float = 0f,

    val isGenerating: Boolean = false,

val isPlaying: Boolean = false,
    val queueSize: Int = 0,

    // ===== Statistics =====

    val estimatedDurationMs: Long = 0L,
val debugMessage: String = "Idle",

val wordCount: Int = 0,

    val paragraphCount: Int = 0,

    val estimatedChunks: Int = 0,

    val chunks: List<Chunk> = emptyList(),

    // ===== Live Production Dashboard =====

    val currentJobTitle: String = "",

    val currentChunk: Int = 0,

    val totalChunks: Int = 0,

    val processedCharacters: Int = 0,

    val remainingCharacters: Int = 0,

    val estimatedRemainingMs: Long = 0L,

    // ===== Output =====

    val generatedAudio: String? = null,

    val canPlay: Boolean = false,

    val canShare: Boolean = false,

    val canExport: Boolean = false,

    // ===== Provider Status =====

    val providerReady: Boolean = true,

    val providerStatus: String = ""

) {

    val characterCount: Int
        get() = text.length

}
