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

    private val voiceEngine: VoiceEngine = when (

        val provider = providerManager.getCurrentProvider()

    ) {

        is AndroidVoiceProvider -> provider.engine

        is PiperVoiceProvider -> provider.engine

        is KokoroVoiceProvider -> provider.engine

        else -> AndroidVoiceProvider(context).engine

    }

    val voiceManager = VoiceManager(

        textProcessor = textProcessor,

        chunkEngine = chunkEngine,

        queueEngine = queueEngine

    )

    private val audioMerger: AudioMerger =
        AndroidAudioMerger()

    val voicePipeline = VoicePipeline(

        voiceEngine = voiceEngine,

        audioMerger = audioMerger

    )

}
