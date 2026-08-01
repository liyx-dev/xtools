package com.liyx.xtools.core.media

/**
 * Validates PCM WAV headers.
 */
class WavValidator {

    fun validate(
        header: WavHeader
    ) {

        if (header.audioFormat != WavConstants.PCM_FORMAT) {

            throw UnsupportedFormatException(
                "Only PCM WAV files are supported."
            )

        }

        if (header.channels <= 0) {

            throw InvalidWavException(
                "Invalid channel count."
            )

        }

        if (header.sampleRate <= 0) {

            throw InvalidWavException(
                "Invalid sample rate."
            )

        }

        if (
            header.bitsPerSample != 8 &&
            header.bitsPerSample != 16 &&
            header.bitsPerSample != 24 &&
            header.bitsPerSample != 32
        ) {

            throw UnsupportedFormatException(
                "Unsupported bit depth."
            )

        }

        if (header.dataSize <= 0) {

            throw CorruptAudioException(
                "No audio data found."
            )

        }

    }

}
