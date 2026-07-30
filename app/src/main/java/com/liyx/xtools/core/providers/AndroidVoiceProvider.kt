package com.liyx.xtools.core.providers

import android.content.Context
import com.liyx.xtools.core.voice.AndroidTtsEngine

class AndroidVoiceProvider(

    context: Context

) : VoiceProvider {

    override val id = "android"

    override val displayName = "System"

    override val isOffline = true

    override val engine = AndroidTtsEngine(context)

    init {

        engine.initialize()

    }

    override fun isAvailable(): Boolean {

        return true

    }

}
