package com.liyx.xtools.core.providers

import ai.onnxruntime.OrtSession
import com.liyx.xtools.core.inference.LiyXModelLoader
import com.liyx.xtools.core.inference.LiyXModelVerifier
import com.liyx.xtools.core.inference.LiyXSessionManager
import java.io.File

class PiperSessionProvider(

    private val runtime: PiperRuntime,
    private val verifier: LiyXModelVerifier,
    private val sessionManager: LiyXSessionManager

) {

    fun getSession(

        model: PiperModel

    ): OrtSession? {

        val modelFile = File(
            runtime.modelsDirectory,
            "${model.id}.onnx"
        )

        if (!verifier.verify(modelFile.absolutePath)) {
            return null
        }

        return sessionManager.getSession(
            modelFile.absolutePath
        )
    }

}
