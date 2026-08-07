package com.liyx.xtools.core.inference

import ai.onnxruntime.OrtSession

class LiyXSessionManager(

    private val loader: LiyXModelLoader

) {

    private val sessions = mutableMapOf<String, OrtSession>()

    fun getSession(

        modelPath: String

    ): OrtSession? {

        sessions[modelPath]?.let {

            return it

        }

        val session = loader.loadModel(modelPath)

        if (session != null) {

            sessions[modelPath] = session

        }

        return session

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
        
