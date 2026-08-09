package com.liyx.xtools.core.providers

import android.content.Context
import com.liyx.xtools.core.voice.PiperEngine
import com.liyx.xtools.core.voice.VoiceInfo

class PiperVoiceProvider(
    context: Context
) : VoiceProvider {

    override val id = "piper"

    override val displayName = "Piper AI Voices"

    override val isOffline = true

    private val runtime = PiperRuntime(
        binaryPath = context.filesDir.absolutePath + "/piper/bin/piper",
        modelsDirectory = context.filesDir.absolutePath + "/piper/models",
        cacheDirectory = context.cacheDir.absolutePath + "/piper/cache",
        tempDirectory = context.cacheDir.absolutePath + "/piper/temp"
    )

    override val engine = PiperEngine(runtime)

    private val modelDetector =
    PiperModelDetector(runtime)

private val downloader =
    PiperDownloadManager(runtime)

private val modelManager =
    PiperModelManager(
        modelDetector,
        downloader
    )

    override fun isAvailable(): Boolean {
        return true
    }

   override fun getVoices(): List<VoiceInfo> {

    return modelManager
        .getModels()
        .filter { it.downloaded }
        .map {

            VoiceInfo(
                id = it.id,
                name = it.name,
                locale = it.language,
                provider = id,
                networkRequired = false,
                quality = "Studio",
                gender = null,
                isOffline = true,
                isInstalled = true
            )

        }

}

}
