package com.liyx.xtools.core.providers

import com.liyx.xtools.core.voice.PiperEngine

class PiperVoiceProvider : VoiceProvider {

    override val id = "piper"

    override val displayName = "Piper AI"

    override val isOffline = true

    val engine = PiperEngine()

    override fun isAvailable(): Boolean {

        return false

    }

}
