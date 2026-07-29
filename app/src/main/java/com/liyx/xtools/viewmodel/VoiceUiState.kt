package com.liyx.xtools.viewmodel

import com.liyx.xtools.core.models.Chunk

data class VoiceUiState(

    val title: String = "",

    val text: String = "",

    val selectedVoice: String = "Default",

    val speed: Float = 1f,

    val pitch: Float = 1f,

    val progress: Float = 0f,

    val isGenerating: Boolean = false,

    val queueSize: Int = 0,

    val estimatedDurationMs: Long = 0L,

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

    val estimatedRemainingMs: Long = 0L

) {

    val characterCount: Int
        get() = text.length

}
