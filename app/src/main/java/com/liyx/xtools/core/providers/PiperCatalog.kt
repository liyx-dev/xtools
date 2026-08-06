package com.liyx.xtools.core.providers

class PiperCatalog {

    fun getModels(): List<PiperModel> {

        return listOf(

            PiperModel(
                id = "en_US-lessac-medium",
                name = "English • Lessac",
                language = "English",
                modelUrl = "https://huggingface.co/rhasspy/piper-voices/resolve/main/en/en_US/lessac/medium/en_US-lessac-medium.onnx",
                configUrl = "https://huggingface.co/rhasspy/piper-voices/resolve/main/en/en_US/lessac/medium/en_US-lessac-medium.onnx.json",
                downloaded = false
            ),

            PiperModel(
                id = "en_US-amy-medium",
                name = "English • Amy",
                language = "English",
                modelUrl = "https://huggingface.co/rhasspy/piper-voices/resolve/main/en/en_US/amy/medium/en_US-amy-medium.onnx",
                configUrl = "https://huggingface.co/rhasspy/piper-voices/resolve/main/en/en_US/amy/medium/en_US-amy-medium.onnx.json",
                downloaded = false
            ),

            PiperModel(
                id = "en_GB-alan-medium",
                name = "English • Alan UK",
                language = "English UK",
                modelUrl = "https://huggingface.co/rhasspy/piper-voices/resolve/main/en/en_GB/alan/medium/en_GB-alan-medium.onnx",
                configUrl = "https://huggingface.co/rhasspy/piper-voices/resolve/main/en/en_GB/alan/medium/en_GB-alan-medium.onnx.json",
                downloaded = false
            ),

            PiperModel(
                id = "fr_FR-siwis-medium",
                name = "French • Siwis",
                language = "French",
                modelUrl = "https://huggingface.co/rhasspy/piper-voices/resolve/main/fr/fr_FR/siwis/medium/fr_FR-siwis-medium.onnx",
                configUrl = "https://huggingface.co/rhasspy/piper-voices/resolve/main/fr/fr_FR/siwis/medium/fr_FR-siwis-medium.onnx.json",
                downloaded = false
            )

        )

    }

}
