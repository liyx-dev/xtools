package com.liyx.xtools.core.providers

class PiperRuntimeValidator(

    private val runtimeManager: PiperRuntimeManager,

    private val binaryDetector: PiperBinaryDetector,

    private val modelDetector: PiperModelDetector

) {

    fun validate(

        model: PiperModel

    ): Boolean {

        return runtimeManager.isInstalled() &&
            binaryDetector.isExecutable() &&
            modelDetector.isInstalled(model)

    }

}
