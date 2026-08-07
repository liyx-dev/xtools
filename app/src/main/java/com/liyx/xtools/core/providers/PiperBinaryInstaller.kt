package com.liyx.xtools.core.providers

import com.liyx.xtools.core.download.DownloadManager
import com.liyx.xtools.core.download.DownloadRequest
import java.io.File

class PiperBinaryInstaller(

    private val runtime: PiperRuntime

) {

    private val downloader = DownloadManager()

    /**
     * Change this later to the official binary
     * release URL for Android.
     */
    private val binaryUrl = ""

    fun install(): Boolean {

        val binary = File(runtime.binaryPath)

        if (binary.exists()) {
            return true
        }

        binary.parentFile?.mkdirs()

        val result = downloader.download(

            DownloadRequest(

                url = binaryUrl,

                outputFile = binary.absolutePath

            )

        )

        if (!result.success) {
            return false
        }

        binary.setExecutable(true)

        return true

    }

}
