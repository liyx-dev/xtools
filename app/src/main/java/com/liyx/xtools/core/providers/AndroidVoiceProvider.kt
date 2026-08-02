package com.liyx.xtools.core.providers

import android.content.Context
import com.liyx.xtools.core.voice.AndroidTtsEngine
import com.liyx.xtools.core.voice.VoiceInfo

class AndroidVoiceProvider(

    context: Context

) : VoiceProvider {

    override val id = "android"

    override val displayName = "Android System TTS"

    override val isOffline = true

    override val engine = AndroidTtsEngine(context)

    init {

        engine.initialize()

    }

    override fun isAvailable(): Boolean = true

    override fun getVoices(): List<VoiceInfo> {

        return engine.getAvailableVoices()

    }

}
