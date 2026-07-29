package com.liyx.xtools.core.providers

import java.io.File

class PiperModelDetector(

    private val runtime: PiperRuntime

) {

    fun modelExists(

        model: PiperModel

    ): Boolean {

        val modelFile = File(

            runtime.modelsDirectory,

            "${model.id}.onnx"

        )

        return modelFile.exists()

    }

    fun configExists(

        model: PiperModel

    ): Boolean {

        val configFile = File(

            runtime.modelsDirectory,

            "${model.id}.onnx.json"

        )

        return configFile.exists()

    }

    fun isInstalled(

        model: PiperModel

    ): Boolean {

        return modelExists(model) &&
            configExists(model)

    }

}
