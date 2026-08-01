package com.liyx.xtools.core.media

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
