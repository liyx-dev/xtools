package com.liyx.xtools.core.voice

import com.liyx.xtools.core.media.AudioMerger
import com.liyx.xtools.core.media.MergeFailedException
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

            return false

        }

        try {

            val mergedPCM = ArrayList<Byte>()

            var header = reader.read(
                File(inputFiles.first())
            ).first

            validator.validate(header)

            inputFiles.forEachIndexed { index, path ->

                val (currentHeader, pcm) =

                    reader.read(File(path))

                validator.validate(currentHeader)

                if (index > 0) {

                    requireCompatible(

                        header,

                        currentHeader

                    )

                }

                mergedPCM.addAll(
                    pcm.toList()
                )

            }

            writer.write(

                File(outputFile),

                header.copy(

                    dataSize = mergedPCM.size

                ),

                mergedPCM.toByteArray()

            )

            return true

        } catch (e: Exception) {

            e.printStackTrace()

            return false

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
