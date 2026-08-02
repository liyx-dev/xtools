package com.liyx.xtools.core.providers

import com.liyx.xtools.core.voice.VoiceEngine
import com.liyx.xtools.core.voice.VoiceInfo

interface VoiceProvider {

    val id: String

    val displayName: String

    val isOffline: Boolean

    /**
     * Voice engine exposed by this provider.
     */
    val engine: VoiceEngine

    fun isAvailable(): Boolean
fun getVoices(): List<VoiceInfo>
}
