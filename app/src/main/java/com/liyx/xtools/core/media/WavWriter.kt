package com.liyx.xtools.core.media

import java.io.File

/**
 * Writes PCM WAV files.
 *
 * Commit 3.1:
 * Foundation only.
 * Writing implementation
 * arrives in Commit 3.2.
 */
class WavWriter {

    fun write(

        header: WavHeader,

        audioData: ByteArray,

        output: File

    ) {

        require(output.parentFile != null)

        throw NotImplementedError(
            "WAV writing will be implemented in Commit 3.2."
        )

    }

}
