package com.liyx.xtools.core.voice

import com.liyx.xtools.core.jobs.VoiceJob
import com.liyx.xtools.core.storage.AudioStorageManager
import java.util.UUID

/**
 * VoiceManager
 *
 * Central coordinator for all voice generation.
 *
 * Step 1:
 * - Owns queue
 * - Owns processing state
 * - Owns callbacks
 *
 * Actual processing will be added in Step 2.
 */
class VoiceManager(

    private val textProcessor: TextProcessor,

    private val chunkEngine: ChunkEngine,

    private val queueEngine: QueueEngine,

    private val voicePipeline: VoicePipeline,

    private val audioStorageManager: AudioStorageManager

) {

    /**
     * Prevent multiple jobs
     * from running simultaneously.
     */
    private var processing = false

    /**
     * Currently processing job.
     */
    private var currentJob: VoiceJob? = null

    /**
     * UI callbacks.
     */
    var onJobStarted: ((VoiceJob) -> Unit)? = null

    var onJobProgress: ((VoiceJob) -> Unit)? = null

    var onJobCompleted: ((VoiceJob, String) -> Unit)? = null

    var onJobFailed: ((VoiceJob) -> Unit)? = null

    /**
     * Creates a new voice job
     * and adds it to the queue.
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
     * Returns the next queued job.
     */
    fun nextJob(): VoiceJob? {

        return queueEngine.next()

    }

    /**
     * Peek current queued job.
     */
    fun peekJob(): VoiceJob? {

        return queueEngine.peek()

    }

    /**
     * Queue size.
     */
    fun queueSize(): Int {

        return queueEngine.size()

    }

    /**
     * Returns true while
     * a generation is running.
     */
    fun isProcessing(): Boolean {

        return processing

    }

    /**
     * Internal processing flag.
     */
    fun setProcessing(

        value: Boolean

    ) {

        processing = value

    }

    /**
     * Current active job.
     */
    fun currentJob(): VoiceJob? {

        return currentJob

    }

    /**
     * Set current active job.
     */
    fun setCurrentJob(

        job: VoiceJob?

    ) {

        currentJob = job

    }

    /**
     * Remove all waiting jobs.
     */
    fun clearQueue() {

        queueEngine.clear()

    }
}
