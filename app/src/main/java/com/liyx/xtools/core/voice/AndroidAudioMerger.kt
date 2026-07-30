package com.liyx.xtools.core.voice

import java.io.File

/**
 * Temporary Android merger.
 *
 * Until FFmpeg is integrated,
 * this merger returns the first generated
 * chunk as the final audio.
 *
 * This allows the entire voice pipeline
 * to work end-to-end.
 */
class AndroidAudioMerger : AudioMerger {

    override fun merge(

        inputFiles: List<String>,

        outputFile: String

    ): Boolean {

        if (inputFiles.isEmpty()) {

            return false

        }

        val first = File(inputFiles.first())

        if (!first.exists()) {

            return false

        }

        return try {

            first.copyTo(

                File(outputFile),

                overwrite = true

            )

            true

        } catch (e: Exception) {

            false

        }

    }

}
