package com.liyx.xtools.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.liyx.xtools.AppContainer

class VoiceViewModelFactory(

    private val appContainer: AppContainer

) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(

        modelClass: Class<T>

    ): T {

        if (

            modelClass.isAssignableFrom(

                VoiceViewModel::class.java

            )

        ) {

            return VoiceViewModel(

                voiceManager = appContainer.voiceManager,

                voiceEngine =

                    appContainer.providerManager
                        .getCurrentEngine(),

                audioMerger =

                    appContainer.audioMerger,

                audioStorageManager =
    appContainer.audioStorageManager,

audioPlayer =
        appContainer.audioPlayer,

    audioLibraryManager =
        appContainer.audioLibraryManager

            ) as T

        }

        throw IllegalArgumentException(

            "Unknown ViewModel"

        )

    }

}
