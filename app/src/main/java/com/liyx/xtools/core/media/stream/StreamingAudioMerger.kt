package com.liyx.xtools.core.media.stream

import com.liyx.xtools.core.media.AudioMerger
import com.liyx.xtools.core.media.MergeFailedException
import com.liyx.xtools.core.media.WavHeader
import com.liyx.xtools.core.media.WavReader
import com.liyx.xtools.core.media.WavValidator
import java.io.File

class StreamingAudioMerger : AudioMerger {

    private val reader = WavReader()
    private val validator = WavValidator()

    override fun merge(

        inputFiles: List<String>,

        outputFile: String

    ): Boolean {

        if (inputFiles.isEmpty()) return false

        return try {

            val firstFile = File(inputFiles.first())

            val (header, firstPCM) = reader.read(firstFile)

            validator.validate(header)

            val writer = StreamingWavWriter(

                File(outputFile)

            )

            writer.start(header)

            writer.appendPCM(firstPCM)

            for (i in 1 until inputFiles.size) {

                val (currentHeader, pcm) =

                    reader.read(File(inputFiles[i]))

                validator.validate(currentHeader)

                requireCompatible(

                    header,

                    currentHeader

                )

                writer.appendPCM(pcm)

            }

            writer.finish()

            true

        } catch (e: Exception) {

            e.printStackTrace()

            false

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
