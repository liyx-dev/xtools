package com.liyx.xtools.core.voice

/**
 * VoiceEngine
 *
 * Every voice provider in Xtools must implement this interface.
 */
interface VoiceEngine {
    fun initialize()
    fun getAvailableVoices(): List<String>
    fun setVoice(voice: String)
    fun setSpeed(speed: Float)
    fun setPitch(pitch: Float)
    fun generate(text: String)
    fun stop()
    fun release()
}
