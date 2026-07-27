package com.liyx.xtools.viewmodel

data class VoiceUiState(

    val title: String = "",

    val text: String = "",

    val selectedVoice: String = "Default",

    val speed: Float = 1.0f,

    val pitch: Float = 1.0f,

    val progress: Float = 0f,

    val isGenerating: Boolean = false,

    val queueSize: Int = 0,

    val estimatedDurationMs: Long = 0L

) {

    val characterCount: Int

        get() = text.length

}
