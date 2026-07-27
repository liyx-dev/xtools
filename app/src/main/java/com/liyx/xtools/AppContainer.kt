package com.liyx.xtools

import android.content.Context
import com.liyx.xtools.core.voice.*

class AppContainer(
    context: Context
) {

    private val textProcessor = TextProcessor()

    private val chunkEngine: ChunkEngine =
        SmartChunkEngine()

    private val queueEngine = QueueEngine()

    private val voiceEngine: VoiceEngine =
        AndroidTtsEngine(context)

    val voiceManager = VoiceManager(
        textProcessor = textProcessor,
        chunkEngine = chunkEngine,
        queueEngine = queueEngine
    )

    val voicePipeline = VoicePipeline(
        voiceEngine = voiceEngine
    )
}
