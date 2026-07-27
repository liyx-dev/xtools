package com.liyx.xtools.core.models

/**
 * Represents the current state
 * of a voice generation job.
 */
enum class VoiceJobStatus {

    PENDING,

    PROCESSING,

    PAUSED,

    COMPLETED,

    FAILED,

    CANCELLED

}
