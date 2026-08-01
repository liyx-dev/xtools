package com.liyx.xtools.core.media

import java.io.File

/**
 * Reads WAV files.
 *
 * Commit 3.1:
 * Foundation only.
 * Parsing will be completed
 * in Commit 3.2.
 */
class WavReader {

    fun readHeader(

        file: File

    ): WavHeader {

        require(file.exists()) {
            "File does not exist."
        }

        throw NotImplementedError(
            "Header parsing will be implemented in Commit 3.2."
        )

    }

    fun readAudio(

        file: File

    ): ByteArray {

        require(file.exists()) {
            "File does not exist."
        }

        throw NotImplementedError(
            "PCM extraction will be implemented in Commit 3.2."
        )

    }

}
