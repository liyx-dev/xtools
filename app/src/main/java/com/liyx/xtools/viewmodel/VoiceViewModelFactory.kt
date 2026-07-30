package com.liyx.xtools.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.liyx.xtools.AppContainer
import com.liyx.xtools.core.storage.AudioStorageManager

class VoiceViewModelFactory(
    private val appContainer: AppContainer,
    private val audioStorageManager: AudioStorageManager? = null
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(VoiceViewModel::class.java)) {

            return VoiceViewModel(
                voiceManager = appContainer.voiceManager,
                voiceEngine = appContainer.providerManager.getCurrentEngine(),
                audioMerger = appContainer.audioMerger,
                audioStorageManager = audioStorageManager
            ) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel class: ${modelClass.name}"
        )
    }
}
