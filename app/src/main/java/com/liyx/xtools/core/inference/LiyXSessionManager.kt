package com.liyx.xtools.core.inference

import ai.onnxruntime.OrtSession

class LiyXSessionManager(

    private val loader: LiyXModelLoader

) {

    private val sessions = mutableMapOf<String, OrtSession>()

    fun getSession(

        modelPath: String

    ): OrtSession? {

        return sessions.getOrPut(modelPath) {

            loader.loadModel(modelPath)

        } ?: sessions[modelPath]

    }

    fun hasSession(

        modelPath: String

    ): Boolean {

        return sessions.containsKey(modelPath)

    }

    fun removeSession(

        modelPath: String

    ) {

        sessions.remove(modelPath)?.close()

    }

    fun clear() {

        sessions.values.forEach {

            it.close()

        }

        sessions.clear()

    }

}
