package com.liyx.xtools.core.voice

import com.liyx.xtools.core.models.Chunk

/**
 * Contract for all chunk engines.
 */
interface ChunkEngine {

    /**
     * Split text into intelligent chunks.
     */
    fun split(text: String): List<Chunk>

}
