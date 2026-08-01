package com.liyx.xtools.core.media

import java.io.File
import java.io.FileOutputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder

/**
 * Production WAV writer.
 *
 * Creates standard PCM WAV files.
 */
class WavWriter {

    fun write(

        output: File,

        header: WavHeader,

        pcm: ByteArray

    ) {

        output.parentFile?.mkdirs()

        FileOutputStream(output).use { out ->

            val dataSize = pcm.size

            val riffSize = 36 + dataSize

            out.write("RIFF".toByteArray())

            out.write(
                littleEndianInt(riffSize)
            )

            out.write("WAVE".toByteArray())

            out.write("fmt ".toByteArray())

            out.write(
                littleEndianInt(16)
            )

            out.write(
                littleEndianShort(
                    header.audioFormat
                )
            )

            out.write(
                littleEndianShort(
                    header.channels
                )
            )

            out.write(
                littleEndianInt(
                    header.sampleRate
                )
            )

            out.write(
                littleEndianInt(
                    header.byteRate
                )
            )

            out.write(
                littleEndianShort(
                    header.blockAlign
                )
            )

            out.write(
                littleEndianShort(
                    header.bitsPerSample
                )
            )

            out.write("data".toByteArray())

            out.write(
                littleEndianInt(dataSize)
            )

            out.write(pcm)

        }

    }

    private fun littleEndianInt(

        value: Int

    ): ByteArray {

        return ByteBuffer

            .allocate(4)

            .order(ByteOrder.LITTLE_ENDIAN)

            .putInt(value)

            .array()

    }

    private fun littleEndianShort(

        value: Int

    ): ByteArray {

        return ByteBuffer

            .allocate(2)

            .order(ByteOrder.LITTLE_ENDIAN)

            .putShort(value.toShort())

            .array()

    }

}
