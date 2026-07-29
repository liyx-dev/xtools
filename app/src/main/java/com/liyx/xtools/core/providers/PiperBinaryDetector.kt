package com.liyx.xtools.core.providers

import java.io.File

class PiperBinaryDetector(

    private val runtime: PiperRuntime

) {

    fun exists(): Boolean {

        return File(runtime.binaryPath).exists()

    }

    fun isExecutable(): Boolean {

        val file = File(runtime.binaryPath)

        return file.exists() && file.canExecute()

    }

}
