package com.liyx.xtools.core.voice

/**
 * AudioExporter
 *
 * Responsible for saving generated audio.
 */
interface AudioExporter {

    fun export(fileName: String)

}
