package com.liyx.xtools.core.providers

import java.io.File

class KokoroModelDetector(

    private val runtime: KokoroRuntime

) {

    fun modelExists(

        model: KokoroModel

    ): Boolean {

        return File(

            runtime.modelsDirectory,

            "${model.id}.onnx"

        ).exists()

    }

    fun isInstalled(

        model: KokoroModel

    ): Boolean {

        return modelExists(model)

    }

}
