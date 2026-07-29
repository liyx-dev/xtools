package com.liyx.xtools.core.providers

import android.content.Context
import com.liyx.xtools.core.voice.AndroidTtsEngine

class AndroidVoiceProvider(

    context: Context

) : VoiceProvider {

    override val id = "android"

    override val displayName =
        "Android Text-to-Speech"

    override val isOffline = true

    val engine = AndroidTtsEngine(context)

    override fun isAvailable(): Boolean {

        return true

    }

}
