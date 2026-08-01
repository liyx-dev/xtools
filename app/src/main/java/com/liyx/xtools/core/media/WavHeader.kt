package com.liyx.xtools.core.media

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

)
