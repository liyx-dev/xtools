package com.liyx.xtools.core.media

import java.io.File
import java.io.RandomAccessFile

/**
 * Production WAV reader.
 *
 * Reads PCM WAV files without assuming
 * a fixed 44-byte header.
 */
class WavReader {

    fun read(
        file: File
    ): Pair<WavHeader, ByteArray> {

        RandomAccessFile(file, "r").use { raf ->

            val riff = ByteArray(4)
            raf.readFully(riff)

            if (String(riff) != "RIFF") {
                throw InvalidWavException("Missing RIFF header.")
            }

            raf.skipBytes(4)

            val wave = ByteArray(4)
            raf.readFully(wave)

            if (String(wave) != "WAVE") {
                throw InvalidWavException("Missing WAVE header.")
            }

            var header: WavHeader? = null

            while (raf.filePointer < raf.length()) {

                val chunkIdBytes = ByteArray(4)
                raf.readFully(chunkIdBytes)

                val chunkId = String(chunkIdBytes)

                val chunkSize = Integer.reverseBytes(
                    raf.readInt()
                )

                when (chunkId) {

                    "fmt " -> {

                        val audioFormat =
                            java.lang.Short.reverseBytes(
                                raf.readShort()
                            ).toInt()

                        val channels =
                            java.lang.Short.reverseBytes(
                                raf.readShort()
                            ).toInt()

                        val sampleRate =
                            Integer.reverseBytes(
                                raf.readInt()
                            )

                        val byteRate =
                            Integer.reverseBytes(
                                raf.readInt()
                            )

                        val blockAlign =
                            java.lang.Short.reverseBytes(
                                raf.readShort()
                            ).toInt()

                        val bitsPerSample =
                            java.lang.Short.reverseBytes(
                                raf.readShort()
                            ).toInt()

                        if (chunkSize > 16) {
                            raf.skipBytes(chunkSize - 16)
                        }

                        header = WavHeader(
                            audioFormat = audioFormat,
                            channels = channels,
                            sampleRate = sampleRate,
                            byteRate = byteRate,
                            blockAlign = blockAlign,
                            bitsPerSample = bitsPerSample,
                            dataSize = 0
                        )
                    }

                    "data" -> {

                        val pcm = ByteArray(chunkSize)
                        raf.readFully(pcm)

                        val wavHeader =
                            header?.copy(
                                dataSize = chunkSize
                            )
                                ?: throw InvalidWavException(
                                    "fmt chunk missing before data."
                                )

                        return wavHeader to pcm
                    }

                    else -> {

                        raf.skipBytes(chunkSize)

                    }
                }
            }

            throw InvalidWavException(
                "No data chunk found."
            )
        }
    }
}
