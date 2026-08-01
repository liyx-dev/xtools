package com.liyx.xtools

import android.content.Context
import com.liyx.xtools.core.providers.AndroidVoiceProvider
import com.liyx.xtools.core.providers.KokoroVoiceProvider
import com.liyx.xtools.core.providers.PiperVoiceProvider
import com.liyx.xtools.core.providers.VoiceProviderManager
import com.liyx.xtools.core.providers.VoiceProviderRegistry
import com.liyx.xtools.core.storage.AudioStorageManager

import com.liyx.xtools.core.voice.AndroidAudioMerger
import com.liyx.xtools.core.voice.ChunkEngine
import com.liyx.xtools.core.voice.QueueEngine
import com.liyx.xtools.core.voice.SmartChunkEngine
import com.liyx.xtools.core.voice.TextProcessor
import com.liyx.xtools.core.voice.VoiceEngine
import com.liyx.xtools.core.voice.VoiceManager
import com.liyx.xtools.core.voice.VoicePipeline

import com.liyx.xtools.core.media.AudioMerger

import com.liyx.xtools.core.player.AudioPlayer
import com.liyx.xtools.core.player.AudioLibraryManager
import com.liyx.xtools.core.export.AndroidShareManager
import com.liyx.xtools.core.media.AudioExporter
import com.liyx.xtools.core.media.AndroidAudioExporter
import com.liyx.xtools.core.media.MediaEngine

class AppContainer(

    context: Context

) {

    /*
     * Core engines
     */
    private val textProcessor = TextProcessor()

    private val chunkEngine: ChunkEngine =
        SmartChunkEngine()

    private val queueEngine = QueueEngine()

    /*
     * Voice Providers
     */
    private val providerRegistry = VoiceProviderRegistry(

        listOf(

            AndroidVoiceProvider(context),

            PiperVoiceProvider(),

            KokoroVoiceProvider()

        )

    )

    val providerManager = VoiceProviderManager(

        providerRegistry

    )

    /*
     * Current Voice Engine
     */
    private val voiceEngine: VoiceEngine =

        providerManager
            .getCurrentProvider()
            ?.engine

            ?: AndroidVoiceProvider(context).engine

    /*
     * Audio Merger
     */
    val audioMerger: AudioMerger =
        AndroidAudioMerger()

val audioExporter: AudioExporter =
    AndroidAudioExporter(context)

val mediaEngine = MediaEngine(

    audioMerger = audioMerger,

    audioExporter = audioExporter

)

    /*
     * Audio Storage
     */
    val audioStorageManager =
        AudioStorageManager(context)
/*
 * Shared Audio Library
 */
val audioLibraryManager =
    AudioLibraryManager(audioStorageManager).apply {

        loadLibrary()

    }

/*
 * Audio Player
 */
val audioPlayer = AudioPlayer(context)


val androidShareManager =

    AndroidShareManager(context) 


   /*
     * Voice Pipeline
     */
    val voicePipeline = VoicePipeline(

        voiceEngine = voiceEngine,

        audioMerger = audioMerger

    )

    /*
     * Voice Manager
     */
    val voiceManager = VoiceManager(

        textProcessor = textProcessor,

        chunkEngine = chunkEngine,

        queueEngine = queueEngine,

        voicePipeline = voicePipeline,

        audioStorageManager = audioStorageManager

    )

}
