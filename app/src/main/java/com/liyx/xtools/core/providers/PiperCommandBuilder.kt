package com.liyx.xtools.core.providers

import java.io.File

class PiperCommandBuilder(

    private val runtime: PiperRuntime

) {

    fun build(

        model: PiperModel,

        outputFile: String

    ): List<String> {

        val modelPath = File(

            runtime.modelsDirectory,

            "${model.id}.onnx"

        ).absolutePath

        return listOf(

            runtime.binaryPath,

            "--model",

            modelPath,

            "--output_file",

            outputFile

        )

    }

}
