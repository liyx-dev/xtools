package com.liyx.xtools.core.inference

import ai.onnxruntime.OrtSession
import java.io.File

class LiyXModelLoader(

    private val engine: LiyXInferenceEngine

) {

    fun loadModel(

        modelPath: String

    ): OrtSession? {

        return try {

            val file = File(modelPath)

            if (!file.exists()) {

                return null

            }

            engine.environment().createSession(

                file.absolutePath,

                OrtSession.SessionOptions()

            )

        } catch (e: Exception) {

            null

        }

    }

}
