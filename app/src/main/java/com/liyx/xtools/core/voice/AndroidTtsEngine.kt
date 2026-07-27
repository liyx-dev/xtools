package com.liyx.xtools.core.voice

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import java.io.File
import java.util.Locale

/**
 * Android implementation of VoiceEngine.
 */
class AndroidTtsEngine(

    private val context: Context

) : VoiceEngine, TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null

    private var initialized = false

    private var speechRate = 1.0f

    private var speechPitch = 1.0f

    private var selectedVoice: String? = null

    override fun initialize() {

        if (tts == null) {

            tts = TextToSpeech(context, this)

        }

    }

    override fun onInit(status: Int) {

        if (status == TextToSpeech.SUCCESS) {

            initialized = true

            tts?.language = Locale.getDefault()

            tts?.setSpeechRate(speechRate)

            tts?.setPitch(speechPitch)

        }

    }

    override fun getAvailableVoices(): List<String> {

        if (!initialized) return emptyList()

        return tts?.voices
            ?.map { it.name }
            ?.sorted()
            ?: emptyList()

    }

    override fun setVoice(voice: String) {

        selectedVoice = voice

        tts?.voices
            ?.firstOrNull { it.name == voice }
            ?.let {

                tts?.voice = it

            }

    }

    override fun setSpeed(speed: Float) {

        speechRate = speed

        tts?.setSpeechRate(speed)

    }

    override fun setPitch(pitch: Float) {

        speechPitch = pitch

        tts?.setPitch(pitch)

    }

    /**
     * Speak immediately.
     */
    override fun generate(text: String) {

        if (!initialized) return

        tts?.speak(

            text,

            TextToSpeech.QUEUE_ADD,

            null,

            "LIVE_${System.currentTimeMillis()}"

        )

    }

    /**
     * Generate speech to an audio file.
     */
    override fun generateToFile(

        text: String,

        outputPath: String

    ): Boolean {

        if (!initialized) return false

        val file = File(outputPath)

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            val result = tts?.synthesizeToFile(

                text,

                Bundle(),

                file,

                "FILE_${System.currentTimeMillis()}"

            )

            result == TextToSpeech.SUCCESS

        } else {

            false

        }

    }

    override fun stop() {

        tts?.stop()

    }

    override fun release() {

        tts?.stop()

        tts?.shutdown()

        tts = null

        initialized = false

    }

}
