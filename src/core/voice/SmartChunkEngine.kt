package com.liyx.xtools.core.voice

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
class ChunkEngine(
    private val maxChunkSize: Int = 1500
) {

    fun split(text: String): List<String> {

        if (text.isBlank()) return emptyList()

        val chunks = mutableListOf<String>()

        val paragraphs = text
            .trim()
            .split(Regex("\\n\\s*\\n"))

        for (paragraph in paragraphs) {

            if (paragraph.length <= maxChunkSize) {

                chunks.add(paragraph.trim())

            } else {

                splitParagraph(paragraph, chunks)

            }
        }

        return chunks
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
}
