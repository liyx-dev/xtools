package com.liyx.xtools.app.viewmodel

import com.liyx.xtools.core.voice.VoiceManager

/**
 * Connects the UI to the Voice Engine.
 */
class VoiceViewModel(

    private val manager: VoiceManager

) {

    fun generate(

        title: String,

        text: String

    ) {

        manager.createJob(

            title,

            text

        )

    }

}
