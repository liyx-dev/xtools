package com.liyx.xtools.core.voice

import com.liyx.xtools.core.media.AudioMerger
import com.liyx.xtools.core.media.MergeFailedException
import com.liyx.xtools.core.media.stream.StreamingWavWriter

import com.liyx.xtools.core.media.WavReader
import com.liyx.xtools.core.media.WavValidator
import java.io.File

class AndroidAudioMerger : AudioMerger {

    private val reader = WavReader()

    private val validator = WavValidator()

    override fun merge(

        inputFiles: List<String>,

        outputFile: String

    ): Boolean {

        if (inputFiles.isEmpty()) {
            return false
        }

        return try {

            val firstHeader =
                reader.read(File(inputFiles.first())).first

            validator.validate(firstHeader)

  
          val writer = StreamingWavWriter(

    File(outputFile)

)

writer.start(

    firstHeader

)


            inputFiles.forEachIndexed { index, path ->

                val (header, pcm) =

                    reader.read(File(path))

                validator.validate(header)

                if (index > 0) {

                    requireCompatible(
                        firstHeader,
                        header
                    )

                }

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

        first: com.liyx.xtools.core.media.WavHeader,

        second: com.liyx.xtools.core.media.WavHeader

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
