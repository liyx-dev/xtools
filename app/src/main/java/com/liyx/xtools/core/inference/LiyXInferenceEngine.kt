package com.liyx.xtools.core.inference

import ai.onnxruntime.OrtEnvironment

class LiyXInferenceEngine {

    private val environment: OrtEnvironment by lazy {
        OrtEnvironment.getEnvironment()
    }

    fun environment(): OrtEnvironment = environment

    fun version(): String = OrtEnvironment.getVersion()
}
