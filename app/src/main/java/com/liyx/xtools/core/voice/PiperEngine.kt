package com.liyx.xtools.core.voice

class PiperEngine : VoiceEngine {

    override fun initialize() {
        // TODO: Initialize Piper runtime
    }

    override fun getAvailableVoices(): List<String> {
        return emptyList()
    }

    override fun setVoice(voice: String) {
        // TODO
    }

    override fun setSpeed(speed: Float) {
        // TODO
    }

    override fun setPitch(pitch: Float) {
        // Piper may ignore pitch depending on model.
    }

    override fun generate(text: String) {
        // TODO
    }

    override fun generateToFile(
        text: String,
        outputPath: String
    ): Boolean {

        return false

    }

    override fun stop() {
        // TODO
    }

    override fun release() {
        // TODO
    }

}
