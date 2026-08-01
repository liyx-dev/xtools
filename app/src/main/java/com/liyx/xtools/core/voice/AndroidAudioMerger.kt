package com.liyx.xtools.core.voice

import android.util.Log
import com.liyx.xtools.core.media.AudioMerger
import com.liyx.xtools.core.media.MergeFailedException
import com.liyx.xtools.core.media.WavHeader
import com.liyx.xtools.core.media.WavReader
import com.liyx.xtools.core.media.WavValidator
import com.liyx.xtools.core.media.WavWriter
import java.io.File

class AndroidAudioMerger : AudioMerger {

    private val reader = WavReader()

    private val writer = WavWriter()

    private val validator = WavValidator()

    override fun merge(

        inputFiles: List<String>,

        outputFile: String

    ): Boolean {

        if (inputFiles.isEmpty()) {

            Log.e("XTOOLS_MERGER", "No input files")

            return false

        }

        try {

            Log.d("XTOOLS_MERGER", "========== MERGE START ==========")
            Log.d("XTOOLS_MERGER", "Input files = ${inputFiles.size}")

            val mergedPCM = ArrayList<Byte>()

            val header = reader.read(
                File(inputFiles.first())
            ).first

            validator.validate(header)

            Log.d(
                "XTOOLS_MERGER",
                "Header OK  SampleRate=${header.sampleRate}  Channels=${header.channels}"
            )

            inputFiles.forEachIndexed { index, path ->

                Log.d(
                    "XTOOLS_MERGER",
                    "Reading chunk ${index + 1}/${inputFiles.size}"
                )

                val (currentHeader, pcm) =
                    reader.read(File(path))

                validator.validate(currentHeader)

                if (index > 0) {

                    requireCompatible(
                        header,
                        currentHeader
                    )

                }

                Log.d(
                    "XTOOLS_MERGER",
                    "Chunk PCM Size = ${pcm.size}"
                )

                mergedPCM.addAll(
                    pcm.toList()
                )

                Log.d(
                    "XTOOLS_MERGER",
                    "Merged PCM Size = ${mergedPCM.size}"
                )
            }

            Log.d(
                "XTOOLS_MERGER",
                "Writing final WAV..."
            )

            writer.write(

                File(outputFile),

                header.copy(

                    dataSize = mergedPCM.size

                ),

                mergedPCM.toByteArray()

            )

            Log.d(
                "XTOOLS_MERGER",
                "Merge COMPLETE"
            )

            return true

        } catch (e: Exception) {

            Log.e(
                "XTOOLS_MERGER",
                "Merge crashed",
                e
            )

            return false

        }

    }

    private fun requireCompatible(

        first: WavHeader,

        second: WavHeader

    ) {

        if (

            first.channels != second.channels ||

            first.sampleRate != second.sampleRate ||

            first.bitsPerSample != second.bitsPerSample ||

            first.audioFormat != second.audioFormat

        ) {

            throw MergeFailedException(

                "WAV formats do not match."

            )

        }

    }

}
