package com.liyx.xtools.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liyx.xtools.core.providers.PiperModel
import com.liyx.xtools.core.providers.PiperModelManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class PiperModelStoreUiState(

    val models: List<PiperModel> = emptyList(),

    val downloadingId: String? = null,

    val message: String = "",

    val error: String? = null

)

class PiperModelStoreViewModel(

    private val modelManager: PiperModelManager

) : ViewModel() {

    private val _uiState =
        MutableStateFlow(
            PiperModelStoreUiState()
        )

    val uiState: StateFlow<PiperModelStoreUiState> =
        _uiState.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {

        _uiState.value =
            _uiState.value.copy(
                models = modelManager.refresh(),
                error = null
            )

    }

    fun download(modelId: String) {

        if (_uiState.value.downloadingId != null) {
            return
        }

        val model =
            modelManager.getModelById(modelId)
                ?: return

        if (model.downloaded) {
            refresh()
            return
        }

        _uiState.value =
            _uiState.value.copy(
                downloadingId = modelId,
                message = "Downloading ${model.name}...",
                error = null
            )

        viewModelScope.launch(Dispatchers.IO) {

            val success =
                modelManager.download(modelId)

            val updatedModels =
                modelManager.refresh()

            _uiState.value =
                if (success) {

                    PiperModelStoreUiState(
                        models = updatedModels,
                        message = "${model.name} installed successfully."
                    )

                } else {

                    PiperModelStoreUiState(
                        models = updatedModels,
                        error = "Failed to install ${model.name}."
                    )

                }

        }

    }

}
