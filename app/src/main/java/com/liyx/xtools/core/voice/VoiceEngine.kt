package com.liyx.xtools.core.voice

/**
 * VoiceEngine
 *
 * Every voice provider in Xtools must implement this interface.
 */
interface VoiceEngine {

    /**
     * Initialize the engine.
     */
    fun initialize()

    /**
     * List available voices.
     */
    fun getAvailableVoices(): List<VoiceInfo>

    /**
     * Select a voice.
     */
    fun setVoice(voice: String)

    /**
     * Speech rate.
     */
    fun setSpeed(speed: Float)

    /**
     * Voice pitch.
     */
    fun setPitch(pitch: Float)

    /**
     * Speak text aloud.
     */
    fun generate(text: String)

    /**
     * Generate speech directly to an audio file.
     *
     * Returns true if the request was accepted.
     */
    fun generateToFile(
        text: String,
        outputPath: String
    ): Boolean

    /**
     * Stop current generation.
     */
    fun stop()

    /**
     * Release all resources.
     */
    fun release()

fun applyConfig(config: VoiceConfig)

}
