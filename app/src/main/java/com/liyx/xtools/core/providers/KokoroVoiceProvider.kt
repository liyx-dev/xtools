package com.liyx.xtools.core.providers

import com.liyx.xtools.core.voice.KokoroEngine

class KokoroVoiceProvider : VoiceProvider {

    override val id = "kokoro"

    override val displayName = "Kokoro AI"

    override val isOffline = true

    override val engine = KokoroEngine()

    override fun isAvailable(): Boolean {

        return false

    }

}
