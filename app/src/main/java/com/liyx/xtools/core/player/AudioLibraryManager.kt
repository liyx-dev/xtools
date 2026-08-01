package com.liyx.xtools.core.player

import com.liyx.xtools.core.player.models.AudioRecording
import com.liyx.xtools.core.storage.AudioStorageManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AudioLibraryManager(

    private val audioStorageManager: AudioStorageManager? = null

) {

    private val recordings =
        mutableListOf<AudioRecording>()

    private val _library =
        MutableStateFlow<List<AudioRecording>>(emptyList())

    val library: StateFlow<List<AudioRecording>> =
        _library.asStateFlow()

    fun add(recording: AudioRecording) {

        recordings.add(recording)

        _library.value = recordings.toList()

    }

    fun remove(id: String) {

        recordings.removeAll {

            it.id == id

        }

        _library.value = recordings.toList()

    }

    fun rename(

        id: String,

        newTitle: String

    ) {

        val index = recordings.indexOfFirst {

            it.id == id

        }

        if (index == -1) return

        recordings[index] = recordings[index].copy(

            title = newTitle

        )

        _library.value = recordings.toList()

    }

    fun clear() {

        recordings.clear()

        _library.value = emptyList()

    }

    /**
     * Reload every generated audio
     * from device storage.
     */
    fun loadLibrary() {

        val storage = audioStorageManager ?: return

        recordings.clear()

        storage.getAllAudioFiles().forEach { file ->

            recordings.add(

                AudioRecording(

                    id = file.absolutePath,

                    title = file.nameWithoutExtension,

                    filePath = file.absolutePath,

                    duration = 0L,

                    createdAt = file.lastModified()

                )

            )

        }

        _library.value = recordings.toList()

    }

}
