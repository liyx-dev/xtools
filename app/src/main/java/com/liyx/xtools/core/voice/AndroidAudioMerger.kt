package com.liyx.xtools.core.voice

/**
 * Placeholder implementation.
 *
 * Android has no built-in API
 * for merging audio files.
 *
 * We'll replace this later with
 * an FFmpeg implementation.
 */
class AndroidAudioMerger : AudioMerger {

    override fun merge(

        inputFiles: List<String>,

        outputFile: String

    ): Boolean {

        // TODO:
        // FFmpeg implementation
        return false

    }

}

