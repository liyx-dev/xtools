package com.liyx.xtools

import android.content.Context
import com.liyx.xtools.core.providers.AndroidVoiceProvider
import com.liyx.xtools.core.providers.KokoroVoiceProvider
import com.liyx.xtools.core.providers.PiperVoiceProvider
import com.liyx.xtools.core.providers.VoiceProviderManager
import com.liyx.xtools.core.providers.VoiceProviderRegistry
import com.liyx.xtools.core.voice.*

class AppContainer(

    context: Context

) {

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
     * Voice Manager
     */
    val voiceManager = VoiceManager(

        textProcessor = textProcessor,

        chunkEngine = chunkEngine,

        queueEngine = queueEngine

    )

    /*
     * Audio Merger
     */
    val audioMerger: AudioMerger =
    AndroidAudioMerger()


    /*
     * Voice Pipeline
     */
    val voicePipeline = VoicePipeline(

        voiceEngine = voiceEngine,

        audioMerger = audioMerger

    )

}
