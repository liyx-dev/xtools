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
DebugLogger.log(
    context,
    "initialize() called"
)
        }

    }

    override fun onInit(status: Int) {

DebugLogger.log(
    context,
    "onInit status = $status"
) 
       if (status == TextToSpeech.SUCCESS) {

DebugLogger.log(
    context,
    "TTS initialized successfully"
) 
           initialized = true

            tts?.language = Locale.getDefault()

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

override fun onStart(
    utteranceId: String?
) {

    DebugLogger.log(
        context,
        "onStart()"
    )

}        

        override fun onDone(utteranceId: String?) {
DebugLogger.log(
    context,
    "onDone()"
)
            synchronized(generationLock) {

                generationCompleted = true

                generationLock.notifyAll()

            }

        }

        @Deprecated("Deprecated in Java")
 
       override fun onError(utteranceId: String?) {
DebugLogger.log(
    context,
    "onError()"
)

            synchronized(generationLock) {

                generationCompleted = true

tts?.stop()

generationLock.notifyAll()

            }

        }

    }

)

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
DebugLogger.log(
    context,
    "generateToFile() called"
)

    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
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
            DebugLogger.log(
    context,
    "Output file not created"
)

return false
        }

    }

    generationCompleted = false

    val file = File(outputPath)

    file.parentFile?.mkdirs()

    if (file.exists()) {
        file.delete()
    }
DebugLogger.log(
    context,
    "Calling synthesizeToFile..."
)

    val result = tts?.synthesizeToFile(

        text,

        Bundle(),

        file,

        "FILE_${System.currentTimeMillis()}"

    )

DebugLogger.log(
    context,
    "synthesizeToFile result = $result"
)
    if (result != TextToSpeech.SUCCESS) {
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
