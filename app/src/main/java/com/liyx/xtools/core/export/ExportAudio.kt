package com.liyx.xtools.core.export

data class ExportAudio(

    val title: String,

    val filePath: String,

    val mimeType: String = "audio/wav"

)
