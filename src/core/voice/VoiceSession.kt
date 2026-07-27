package com.liyx.xtools.core.voice

import com.liyx.xtools.core.jobs.VoiceJob
import com.liyx.xtools.core.models.Chunk
import com.liyx.xtools.core.models.ChunkStatus
import com.liyx.xtools.core.models.VoiceJobStatus

/**
 * VoiceSession
 *
 * Controls one active voice generation session.
 */
class VoiceSession(

    val job: VoiceJob

) {

    private var currentChunkIndex = 0

    /**
     * Start processing.
     */
    fun start() {

        job.status = VoiceJobStatus.PROCESSING
        job.startedAt = System.currentTimeMillis()

    }

    /**
     * Pause processing.
     */
    fun pause() {

        job.status = VoiceJobStatus.PAUSED

    }

    /**
     * Resume processing.
     */
    fun resume() {

        job.status = VoiceJobStatus.PROCESSING

    }

    /**
     * Cancel processing.
     */
    fun cancel() {

        job.status = VoiceJobStatus.CANCELLED
        job.finishedAt = System.currentTimeMillis()

    }

    /**
     * Mark session completed.
     */
    fun complete() {

        job.status = VoiceJobStatus.COMPLETED
        job.progress = 1f
        job.finishedAt = System.currentTimeMillis()

    }

    /**
     * Get current chunk.
     */
    fun currentChunk(): Chunk? {

        return job.chunks.getOrNull(currentChunkIndex)

    }

    /**
     * Move to next chunk.
     */
    fun nextChunk(): Chunk? {

        currentChunk()?.status = ChunkStatus.COMPLETED

        currentChunkIndex++

        updateProgress()

        return currentChunk()

    }

    /**
     * Current progress (0.0 - 1.0)
     */
    fun progress(): Float {

        return job.progress

    }

    /**
     * Update progress.
     */
    private fun updateProgress() {

        if (job.chunks.isEmpty()) {

            job.progress = 1f
            return

        }

        job.progress =
            currentChunkIndex.toFloat() /
                    job.chunks.size.toFloat()

    }

    /**
     * Is session finished?
     */
    fun isFinished(): Boolean {

        return currentChunkIndex >= job.chunks.size

    }

}
