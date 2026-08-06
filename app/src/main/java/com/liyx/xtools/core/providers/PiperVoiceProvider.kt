package com.liyx.xtools.core.providers

import com.liyx.xtools.core.voice.PiperEngine
import com.liyx.xtools.core.voice.VoiceInfo

class PiperVoiceProvider : VoiceProvider {

    override val id = "piper"

    override val displayName = "Piper AI Voices"

    override val isOffline = true

    override val engine = PiperEngine()
private val modelManager = PiperModelManager()
    override fun isAvailable(): Boolean {
    return true
}


override fun getVoices(): List<VoiceInfo> {

    return modelManager.getModels().map {

        VoiceInfo(

            id = it.id,

            name = it.name,

            locale = it.language,

            provider = id,

            networkRequired = false,

            quality = "Studio",

            gender = null,

            isOffline = true

        )

    }

}
          

}
