package com.liyx.xtools.core.export

class ExportManager(

    private val shareManager: AndroidShareManager? = null

) {

    fun export(audio: ExportAudio): Boolean {

        val exists = java.io.File(audio.filePath).exists()

        if (!exists) return false

        shareManager?.share(audio)

        return true

    }

}
