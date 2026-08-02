package com.liyx.xtools.core.providers

import com.liyx.xtools.core.voice.KokoroEngine
import com.liyx.xtools.core.voice.VoiceInfo

class KokoroVoiceProvider : VoiceProvider {

    override val id = "kokoro"

    override val displayName = "Kokoro AI"

    override val isOffline = true

    override val engine = KokoroEngine()

    override fun isAvailable(): Boolean {

        return false

    }
override fun getVoices(): List<VoiceInfo> {

    return emptyList()

}


}
