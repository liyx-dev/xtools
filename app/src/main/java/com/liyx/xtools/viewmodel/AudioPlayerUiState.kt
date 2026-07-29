package com.liyx.xtools.viewmodel

data class AudioPlayerUiState(

    val currentFile: String? = null,

    val isPlaying: Boolean = false,

    val currentPosition: Int = 0,

    val duration: Int = 0

)
