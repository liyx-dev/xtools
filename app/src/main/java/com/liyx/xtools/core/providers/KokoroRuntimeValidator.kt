package com.liyx.xtools.core.providers

class KokoroRuntimeValidator(

    private val runtimeManager: KokoroRuntimeManager,

    private val binaryDetector: KokoroBinaryDetector,

    private val modelDetector: KokoroModelDetector

) {

    fun validate(

        model: KokoroModel

    ): Boolean {

        return runtimeManager.isInstalled() &&
            binaryDetector.isExecutable() &&
            modelDetector.isInstalled(model)

    }

}
