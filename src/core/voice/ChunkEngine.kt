package com.liyx.xtools.core.voice

/**
 * ChunkEngine
 *
 * Splits large text into smaller chunks
 * for safe voice generation.
 */
class ChunkEngine(
    private val maxChunkSize: Int = 1500
) {

    fun split(text: String): List<String> {

        if (text.isBlank()) return emptyList()

        val chunks = mutableListOf<String>()

        var start = 0

        while (start < text.length) {

            val end = minOf(start + maxChunkSize, text.length)

            chunks.add(text.substring(start, end))

            start = end

        }

        return chunks

    }

}
