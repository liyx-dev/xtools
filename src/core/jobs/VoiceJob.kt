package com.liyx.xtools.core.jobs

import com.liyx.xtools.core.models.Chunk
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

    var totalCharacters: Int = 0,

    var processedCharacters: Int = 0,

    var outputFile: String? = null,

    var createdAt: Long = System.currentTimeMillis(),

    var startedAt: Long? = null,

    var finishedAt: Long? = null

) {

    /**
     * Total number of chunks.
     */
    fun totalChunks(): Int {

        return chunks.size

    }

    /**
     * Completed chunks.
     */
    fun completedChunks(): Int {

        return chunks.count {

            it.status.name == "COMPLETED"

        }

    }

}
