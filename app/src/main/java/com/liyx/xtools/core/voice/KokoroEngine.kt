package com.liyx.xtools.core.voice

class KokoroEngine : VoiceEngine {

    override fun initialize() {

    }

    override fun getAvailableVoices(): List<VoiceInfo> {

    return emptyList()

}

override fun applyConfig(

    config: VoiceConfig

) {

    setVoice(config.voiceId)

    setSpeed(config.speed)

    setPitch(config.pitch)

}

    override fun setVoice(voice: String) {

    }

    override fun setSpeed(speed: Float) {

    }

    override fun setPitch(pitch: Float) {

    }

    override fun generate(text: String) {

    }

    override fun generateToFile(

        text: String,

        outputPath: String

    ): Boolean {

        return false

    }

    override fun stop() {

    }

    override fun release() {

    }

}
