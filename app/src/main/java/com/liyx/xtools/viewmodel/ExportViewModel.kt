package com.liyx.xtools.viewmodel

import androidx.lifecycle.ViewModel
import com.liyx.xtools.core.export.ExportAudio
import com.liyx.xtools.core.export.ExportManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ExportViewModel(

    private val exportManager: ExportManager =
        ExportManager()

) : ViewModel() {

    private val _exportSuccess =
        MutableStateFlow(false)

    val exportSuccess: StateFlow<Boolean> =
        _exportSuccess.asStateFlow()

    fun export(audio: ExportAudio) {

        _exportSuccess.value =
            exportManager.export(audio)

    }

    fun reset() {

        _exportSuccess.value = false

    }

}
