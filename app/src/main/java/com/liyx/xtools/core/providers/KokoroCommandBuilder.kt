package com.liyx.xtools.core.providers

import java.io.File

class KokoroCommandBuilder(

    private val runtime: KokoroRuntime

) {

    fun build(

        model: KokoroModel,

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

            "--output",

            outputFile

        )

    }

}
