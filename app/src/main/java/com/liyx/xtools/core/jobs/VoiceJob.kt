package com.liyx.xtools.core.jobs

import com.liyx.xtools.core.models.Chunk
import com.liyx.xtools.core.models.ChunkStatus
import com.liyx.xtools.core.models.VoiceJobStatus

/**
 * Represents one complete
 * voice generation task.
 */
data class VoiceJob(

    val id: String,

    val title: String,

    val chunks: MutableList<Chunk>,

    var status: VoiceJobStatus = VoiceJobStatus.PENDING,

    var progress: Float = 0f,

    var totalCharacters: Int = chunks.sumOf { it.characterCount },

    var processedCharacters: Int = 0,

    var outputFile: String? = null,

    var createdAt: Long = System.currentTimeMillis(),

    var startedAt: Long? = null,

    var finishedAt: Long? = null

) {

    /**
     * Total chunks in this job.
     */
    fun totalChunks(): Int = chunks.size

    /**
     * Number of completed chunks.
     */
    fun completedChunks(): Int =
        chunks.count { it.status == ChunkStatus.COMPLETED }

    /**
     * Remaining chunks.
     */
    fun remainingChunks(): Int =
        chunks.count {
            it.status == ChunkStatus.PENDING ||
            it.status == ChunkStatus.PROCESSING
        }

    /**
     * Recalculate progress.
     */
    fun updateProgress() {

        progress =
            if (chunks.isEmpty()) {
                0f
            } else {
                completedChunks().toFloat() / totalChunks()
            }

    }

}
