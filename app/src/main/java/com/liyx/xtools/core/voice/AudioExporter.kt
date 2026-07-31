package com.liyx.xtools.core.voice

/**
 * Exports generated audio
 * to a user-accessible location.
 */
interface AudioExporter {

    fun export(

        sourceFile: String,

        displayName: String

    ): Boolean

}
