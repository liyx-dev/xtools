package com.liyx.xtools.core.voice

import com.liyx.xtools.core.jobs.VoiceJob
import java.util.UUID

/**
 * VoiceManager
 *
 * Main entry point for all
 * voice generation requests.
 */
class VoiceManager(

    private val textProcessor: TextProcessor,

    private val chunkEngine: SmartChunkEngine,

    private val queueEngine: QueueEngine

) {

    /**
     * Creates a new voice generation job.
     */
    fun createJob(

        title: String,

        rawText: String

    ): VoiceJob {

        val cleanedText = textProcessor.clean(rawText)

        val chunks = chunkEngine.split(cleanedText)

        val job = VoiceJob(

            id = UUID.randomUUID().toString(),

            title = title,

            chunks = chunks.toMutableList()

        )

        queueEngine.add(job)

        return job

    }

    /**
     * Returns the next job waiting
     * in the queue.
     */
    fun nextJob(): VoiceJob? {

        return queueEngine.next()

    }

    /**
     * Current queue size.
     */
    fun queueSize(): Int {

        return queueEngine.size()

    }

    /**
     * Clear all waiting jobs.
     */
    fun clearQueue() {

        queueEngine.clear()

    }

}
