package com.liyx.xtools.core.providers

import ai.onnxruntime.OrtSession

class PiperInferenceEngine(

    private val sessionProvider: PiperSessionProvider

) {

    fun isReady(

        model: PiperModel

    ): Boolean {

        val session = sessionProvider.getSession(model)

        return session != null

    }

    fun getSession(

        model: PiperModel

    ): OrtSession? {

        return sessionProvider.getSession(model)

    }

}
