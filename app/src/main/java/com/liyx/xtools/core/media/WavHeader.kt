package com.liyx.xtools.core.media

import java.nio.ByteBuffer
import java.nio.ByteOrder

/**
 * Represents the metadata
 * of a PCM WAV file.
 */
data class WavHeader(

    val audioFormat: Int,

    val channels: Int,

    val sampleRate: Int,

    val byteRate: Int,

    val blockAlign: Int,

    val bitsPerSample: Int,

    val dataSize: Int

) {

    fun toByteArray(): ByteArray {

        val buffer = ByteBuffer
            .allocate(44)
            .order(ByteOrder.LITTLE_ENDIAN)

        buffer.put("RIFF".toByteArray())

        buffer.putInt(36 + dataSize)

        buffer.put("WAVE".toByteArray())

        buffer.put("fmt ".toByteArray())

        buffer.putInt(16)

        buffer.putShort(audioFormat.toShort())

        buffer.putShort(channels.toShort())

        buffer.putInt(sampleRate)

        buffer.putInt(byteRate)

        buffer.putShort(blockAlign.toShort())

        buffer.putShort(bitsPerSample.toShort())

        buffer.put("data".toByteArray())

        buffer.putInt(dataSize)

        return buffer.array()

    }

}
