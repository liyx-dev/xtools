package com.liyx.xtools.core.media

/**
 * Base exception for the
 * Xtools Media Engine.
 */
sealed class MediaException(
    message: String
) : Exception(message)

/**
 * Invalid WAV structure.
 */
class InvalidWavException(
    message: String
) : MediaException(message)

/**
 * Unsupported WAV format.
 */
class UnsupportedFormatException(
    message: String
) : MediaException(message)

/**
 * Audio file is damaged.
 */
class CorruptAudioException(
    message: String
) : MediaException(message)

/**
 * Merge process failed.
 */
class MergeFailedException(
    message: String
) : MediaException(message)
