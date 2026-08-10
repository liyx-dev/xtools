package com.liyx.xtools.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.liyx.xtools.AppContainer

class PiperModelStoreViewModelFactory(

    private val appContainer: AppContainer

) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (
            modelClass.isAssignableFrom(
                PiperModelStoreViewModel::class.java
            )
        ) {

            return PiperModelStoreViewModel(
                appContainer.piperModelManager
            ) as T

        }

        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )

    }

}
