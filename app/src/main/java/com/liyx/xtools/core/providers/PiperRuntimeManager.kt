package com.liyx.xtools.core.providers

import java.io.File

class PiperRuntimeManager(

    private val runtime: PiperRuntime

) {

    fun binaryFile(): File =
        File(runtime.binaryPath)

    fun modelsDirectory(): File =
        File(runtime.modelsDirectory)

    fun cacheDirectory(): File =
        File(runtime.cacheDirectory)

    fun tempDirectory(): File =
        File(runtime.tempDirectory)

    fun prepareRuntime() {

        modelsDirectory().mkdirs()

        cacheDirectory().mkdirs()

        tempDirectory().mkdirs()

    }

    fun binaryExists(): Boolean =
        binaryFile().exists()

    fun modelsDirectoryExists(): Boolean =
        modelsDirectory().exists()

    fun cacheDirectoryExists(): Boolean =
        cacheDirectory().exists()

    fun tempDirectoryExists(): Boolean =
        tempDirectory().exists()

    fun isInstalled(): Boolean {

        return binaryExists() &&
            modelsDirectoryExists()

    }

}
