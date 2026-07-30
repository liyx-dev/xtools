package com.liyx.xtools.core.providers

import java.io.File

class KokoroRuntimeManager(

    private val runtime: KokoroRuntime

) {

    fun binaryExists(): Boolean {

        return File(runtime.binaryPath).exists()

    }

    fun modelsDirectoryExists(): Boolean {

        return File(runtime.modelsDirectory).exists()

    }

    fun isInstalled(): Boolean {

        return binaryExists() &&
            modelsDirectoryExists()

    }

}
