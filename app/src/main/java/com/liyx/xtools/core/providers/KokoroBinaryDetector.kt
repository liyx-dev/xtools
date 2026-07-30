package com.liyx.xtools.core.providers

import java.io.File

class KokoroBinaryDetector(

    private val runtime: KokoroRuntime

) {

    fun exists(): Boolean {

        return File(runtime.binaryPath).exists()

    }

    fun isExecutable(): Boolean {

        val file = File(runtime.binaryPath)

        return file.exists() && file.canExecute()

    }

}
