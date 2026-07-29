package com.liyx.xtools.viewmodel

import androidx.lifecycle.ViewModel
import com.liyx.xtools.core.player.AudioPlayer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class AudioPlayerViewModel(

    private val audioPlayer: AudioPlayer? = null

) : ViewModel() {

    private val _uiState = MutableStateFlow(
        AudioPlayerUiState()
    )

    val uiState: StateFlow<AudioPlayerUiState> =
        _uiState.asStateFlow()

private var progressJob: Job? = null

    fun play(filePath: String) {

        audioPlayer?.play(filePath)
startProgressUpdates()

        _uiState.value = _uiState.value.copy(

            currentFile = filePath,

            isPlaying = true,

            currentPosition = 0,

            duration = audioPlayer?.getDuration() ?: 0

        )

    }

    fun pause() {

        audioPlayer?.pause()

        _uiState.value = _uiState.value.copy(

            isPlaying = false

        )

    }

    fun resume() {

        audioPlayer?.resume()
startProgressUpdates()

        _uiState.value = _uiState.value.copy(

            isPlaying = true

        )

    }

    fun stop() {

        audioPlayer?.stop()
progressJob?.cancel()

        _uiState.value = AudioPlayerUiState()

    }

private fun startProgressUpdates() {

    progressJob?.cancel()

    progressJob = CoroutineScope(Dispatchers.Main).launch {

        while (isActive && audioPlayer?.isPlaying() == true) {

            _uiState.value = _uiState.value.copy(

                currentPosition =
                    audioPlayer.getCurrentPosition(),

                duration =
                    audioPlayer.getDuration()

            )

            delay(250)

        }

    }

}

    fun seekTo(position: Int) {

        audioPlayer?.seekTo(position)

    }

    fun isPlaying(): Boolean {

        return audioPlayer?.isPlaying() ?: false

    }

    fun getCurrentPosition(): Int {

        return audioPlayer?.getCurrentPosition() ?: 0

    }

    fun getDuration(): Int {

        return audioPlayer?.getDuration() ?: 0

    }

    override fun onCleared() {
progressJob?.cancel()

        audioPlayer?.release()

        super.onCleared()

    }

}
