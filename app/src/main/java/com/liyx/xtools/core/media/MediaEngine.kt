package com.liyx.xtools.core.media

class MediaEngine(

    val audioMerger: AudioMerger,

    val audioExporter: AudioExporter

) {

    fun merge(

        inputFiles: List<String>,

        outputFile: String

    ): Boolean {

        return audioMerger.merge(

            inputFiles,

            outputFile

        )

    }

    fun export(

        fileName: String

    ) {

        audioExporter.export(fileName)

    }

}
