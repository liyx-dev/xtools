package com.liyx.xtools.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.liyx.xtools.AppContainer

class AudioLibraryViewModelFactory(

    private val appContainer: AppContainer

) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(

        modelClass: Class<T>

    ): T {

        if (

            modelClass.isAssignableFrom(

                AudioLibraryViewModel::class.java

            )

        ) {

            return AudioLibraryViewModel(

                appContainer.audioLibraryManager

            ) as T

        }

        throw IllegalArgumentException(

            "Unknown ViewModel"

        )

    }

}
