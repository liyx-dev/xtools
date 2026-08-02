package com.liyx.xtools.core.providers

import com.liyx.xtools.core.voice.PiperEngine
import com.liyx.xtools.core.voice.VoiceInfo

class PiperVoiceProvider : VoiceProvider {

    override val id = "piper"

    override val displayName = "Piper AI Voices"

    override val isOffline = true

    override val engine = PiperEngine()

    override fun isAvailable():

 Boolean {

        return false

    }

override fun getVoices(): List<VoiceInfo> {

    return emptyList()

}

}
