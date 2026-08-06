package com.liyx.xtools.core.providers

import java.io.File

data class PiperRuntime(

    val binaryPath: String,

    val modelsDirectory: String,

    val cacheDirectory: String,

    val tempDirectory: String

) {

    fun modelsDir(): File = File(modelsDirectory)

    fun cacheDir(): File = File(cacheDirectory)

    fun tempDir(): File = File(tempDirectory)

    fun binaryFile(): File = File(binaryPath)

}
