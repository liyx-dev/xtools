package com.liyx.xtools.core.voice

/**
 * AudioMerger
 *
 * Contract for merging multiple
 * generated audio files into one.
 */
interface AudioMerger {

    /**
     * Merge audio files.
     *
     * @param inputFiles Ordered list of audio files.
     * @param outputFile Final merged file.
     *
     * @return true if merge succeeded.
     */
    fun merge(

        inputFiles: List<String>,

        outputFile: String

    ): Boolean

}

