package com.liyx.xtools.core.models

/**
 * Represents one unit of text
 * waiting to become audio.
 */
data class Chunk(

    val id: Int,

    val order: Int,

    val text: String,

    val characterCount: Int,

    var estimatedDurationMs: Long = 0L,

    var status: ChunkStatus = ChunkStatus.PENDING,

    var audioFile: String? = null

)
