package com.liyx.xtools.core.media

/**
 * WAV file constants used throughout
 * the Media Engine.
 */
object WavConstants {

    /** Standard PCM WAV header size. */
    const val HEADER_SIZE = 44

    /** PCM audio format. */
    const val PCM_FORMAT = 1

    /** Chunk identifiers. */
    const val RIFF = "RIFF"
    const val WAVE = "WAVE"
    const val FMT = "fmt "
    const val DATA = "data"

}
