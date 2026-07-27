package com.liyx.xtools.core.voice

/**
 * TextProcessor
 *
 * Cleans and prepares text before it reaches
 * the Voice Engine.
 */
class TextProcessor {

    /**
     * Cleans user text.
     */
    fun clean(text: String): String {

        return text
            .replace("\\s+".toRegex(), " ")
            .replace("\n\n+".toRegex(), "\n")
            .trim()

    }

}
