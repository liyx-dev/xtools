package com.liyx.xtools.core.voice

import com.liyx.xtools.core.models.Chunk

/**
 * SmartChunkEngine
 *
 * Splits text intelligently for natural voice generation.
 *
 * Strategy:
 * 1. Split by paragraphs.
 * 2. Keep paragraphs together when possible.
 * 3. If a paragraph is too large, split by sentences.
 * 4. If a sentence is still too large, safely split by length.
 */

    class SmartChunkEngine(
    private val maxChunkSize: Int = 1500
) : ChunkEngine {

    fun split(text: String): List<Chunk> {

        if (text.isBlank()) return emptyList()

        val rawChunks = mutableListOf<String>()

        val paragraphs = text
            .trim()
            .split(Regex("\\n\\s*\\n"))

        for (paragraph in paragraphs) {

            if (paragraph.length <= maxChunkSize) {

                rawChunks.add(paragraph.trim())

            } else {

                splitParagraph(paragraph, rawChunks)

            }
        }

        return rawChunks.mapIndexed { index, chunkText ->

            Chunk(
                id = index + 1,
                order = index + 1,
                text = chunkText,
                characterCount = chunkText.length,
                estimatedDurationMs = estimateDuration(chunkText)
            )

        }

    }

    private fun splitParagraph(
        paragraph: String,
        chunks: MutableList<String>
    ) {

        val sentences = paragraph.split(
            Regex("(?<=[.!?])\\s+")
        )

        var current = StringBuilder()

        for (sentence in sentences) {

            if (current.length + sentence.length + 1 <= maxChunkSize) {

                if (current.isNotEmpty()) {
                    current.append(" ")
                }

                current.append(sentence)

            } else {

                if (current.isNotEmpty()) {
                    chunks.add(current.toString().trim())
                    current = StringBuilder()
                }

                if (sentence.length > maxChunkSize) {

                    splitLongSentence(sentence, chunks)

                } else {

                    current.append(sentence)

                }

            }

        }

        if (current.isNotEmpty()) {
            chunks.add(current.toString().trim())
        }

    }

    private fun splitLongSentence(
        sentence: String,
        chunks: MutableList<String>
    ) {

        var start = 0

        while (start < sentence.length) {

            val end = minOf(
                start + maxChunkSize,
                sentence.length
            )

            chunks.add(sentence.substring(start, end).trim())

            start = end

        }

    }

    /**
     * Rough estimate:
     * ~15 characters per second.
     */
    private fun estimateDuration(text: String): Long {

        val seconds = text.length / 15.0

        return (seconds * 1000).toLong()

    }

}
