package com.liyx.xtools.viewmodel

import androidx.lifecycle.ViewModel
import com.liyx.xtools.core.player.AudioLibraryManager
import com.liyx.xtools.core.player.models.AudioRecording
import kotlinx.coroutines.flow.StateFlow

class AudioLibraryViewModel(

    private val libraryManager: AudioLibraryManager

) : ViewModel() {

    val library: StateFlow<List<AudioRecording>> =
        libraryManager.library

    fun add(recording: AudioRecording) {

        libraryManager.add(recording)

    }

    fun remove(id: String) {

        libraryManager.remove(id)

    }

    fun rename(

        id: String,

        title: String

    ) {

        libraryManager.rename(

            id,

            title

        )

    }

    fun clear() {

        libraryManager.clear()

    }

    fun reload() {

        libraryManager.loadLibrary()

    }

}
