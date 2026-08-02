package com.liyx.xtools.core.voice

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import java.io.File
import java.util.Locale
import com.liyx.xtools.core.utils.DebugLogger

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
    private var generationCompleted = false
    private val generationLock = Object()

    override fun initialize() {
        if (tts == null) {
            tts = TextToSpeech(context, this)
            DebugLogger.log(context, "initialize() called")
        }
    }

    override fun onInit(status: Int) {
        DebugLogger.log(context, "onInit status = $status")

        if (status != TextToSpeech.SUCCESS) {
            DebugLogger.log(context, "TTS init FAILED, status = $status")
            initialized = false
            return
        }

        DebugLogger.log(context, "TTS initialized successfully")

        // Check the default locale is actually usable before committing to it.
        // Many devices/emulators don't have voice data installed for
        // Locale.getDefault(), and setLanguage() will silently fail
        // (LANG_MISSING_DATA / LANG_NOT_SUPPORTED) with speak() then doing nothing.
        val defaultLocale = Locale.getDefault()
        val languageResult = tts?.setLanguage(defaultLocale)

        DebugLogger.log(context, "setLanguage($defaultLocale) result = $languageResult")

        if (languageResult == TextToSpeech.LANG_MISSING_DATA ||
            languageResult == TextToSpeech.LANG_NOT_SUPPORTED
        ) {
            DebugLogger.log(
                context,
                "Default locale unsupported, falling back to US English"
            )

            val fallbackResult = tts?.setLanguage(Locale.US)
            DebugLogger.log(context, "setLanguage(US) fallback result = $fallbackResult")

            if (fallbackResult == TextToSpeech.LANG_MISSING_DATA ||
                fallbackResult == TextToSpeech.LANG_NOT_SUPPORTED
            ) {
                DebugLogger.log(
                    context,
                    "No usable TTS language/voice data available on this device"
                )
                initialized = false
                return
            }
        }

        initialized = true

        tts?.setSpeechRate(speechRate)
        tts?.setPitch(speechPitch)

        selectedVoice?.let { voiceName ->
            tts?.voices
                ?.firstOrNull { it.name == voiceName }
                ?.let {
                    tts?.voice = it
                }
        }

        tts?.setOnUtteranceProgressListener(
            object : UtteranceProgressListener() {

                override fun onStart(utteranceId: String?) {
                    DebugLogger.log(context, "onStart()")
                }

                override fun onDone(utteranceId: String?) {
                    DebugLogger.log(context, "onDone()")
                    synchronized(generationLock) {
                        generationCompleted = true
                        generationLock.notifyAll()
                    }
                }

                @Deprecated("Deprecated in Java")

                override fun onError(utteranceId: String?) {
                    DebugLogger.log(context, "onError()")
                    synchronized(generationLock) {
                        generationCompleted = true
                        tts?.stop()
                        generationLock.notifyAll()
                    }
                }
            }
        )
    }

   override fun getAvailableVoices(): List<VoiceInfo> {

    if (!initialized) return emptyList()

    return tts?.voices
        ?.sortedBy { it.locale.displayName }
        ?.map { voice ->

            VoiceInfo(

                id = voice.name,

                name = buildString {

    append(voice.locale.displayLanguage)

    if (voice.locale.displayCountry.isNotBlank()) {

        append(" (")

        append(voice.locale.displayCountry)

        append(")")

    }

},
              locale = voice.locale.displayName,

                provider = "Android",

                networkRequired = voice.isNetworkConnectionRequired,

                quality =
                    if (voice.quality >= 400) {

                        "High"

                    } else {

                        "Standard"

                    },

                isOffline = !voice.isNetworkConnectionRequired

            )

        }

        ?: emptyList()

}


override fun applyConfig(

    config: VoiceConfig

) {

    setSpeed(config.speed)

    setPitch(config.pitch)

    if (config.voiceId.isNotBlank()) {

        setVoice(config.voiceId)

    }

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
        if (!initialized) {
            DebugLogger.log(context, "generate() called but engine not initialized")
            return
        }

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
        DebugLogger.log(context, "generateToFile() called")

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            DebugLogger.log(context, "generateToFile() unsupported below API 21")
            return false
        }

        if (!initialized) {
            val start = System.currentTimeMillis()

            while (!initialized &&
                System.currentTimeMillis() - start < 5000
            ) {
                try {
                    Thread.sleep(100)
                } catch (_: InterruptedException) {
                }
            }

            if (!initialized) {
                DebugLogger.log(context, "generateToFile() aborted: engine never initialized")
                return false
            }
        }

        generationCompleted = false

        val file = File(outputPath)
        file.parentFile?.mkdirs()

        if (file.exists()) {
            file.delete()
        }

        DebugLogger.log(context, "Calling synthesizeToFile...")

        val result = tts?.synthesizeToFile(
            text,
            Bundle(),
            file,
            "FILE_${System.currentTimeMillis()}"
        )

        DebugLogger.log(context, "synthesizeToFile result = $result")

        if (result != TextToSpeech.SUCCESS) {
            DebugLogger.log(context, "synthesizeToFile() did not return SUCCESS, aborting")
            return false
        }

        synchronized(generationLock) {
            while (!generationCompleted) {
                try {
                    generationLock.wait()
                } catch (e: InterruptedException) {
                    Thread.currentThread().interrupt()
                    return false
                }
            }
        }

        // Give Android a short moment to flush the file.
        repeat(20) {
            if (file.exists() && file.length() > 0L) {
                return true
            }
            Thread.sleep(100)
        }

        DebugLogger.log(context, "Output file not created or empty after wait")
        return false
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

