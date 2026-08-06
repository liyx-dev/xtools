package com.liyx.xtools.core.providers

enum class PiperDownloadStatus {

    SUCCESS,

    ALREADY_DOWNLOADED,

    FAILED

}

class PiperDownloadManager {

    /**
     * The model currently being downloaded.
     * This prepares Xtools for future download
     * progress tracking and download queues.
     */
    private var activeDownload: PiperModel? = null

    /**
     * Starts downloading a Piper model.
     *
     */
    
fun downloadModel(
    model: PiperModel,
    runtime: PiperRuntime
): PiperDownloadStatus {

    if (model.downloaded) {
        return PiperDownloadStatus.ALREADY_DOWNLOADED
    }

    activeDownload = model

    return try {

        val modelsDir = java.io.File(runtime.modelsDirectory)
        modelsDir.mkdirs()

        val modelFile =
            java.io.File(modelsDir, "${model.id}.onnx")

        val configFile =
            java.io.File(modelsDir, "${model.id}.onnx.json")

        downloadFile(
    model.modelUrl,
    modelFile
)

downloadFile(
    model.configUrl,
    configFile
)


        activeDownload = null

        PiperDownloadStatus.SUCCESS

    } catch (e: Exception) {

        activeDownload = null

        PiperDownloadStatus.FAILED
    }
}

private fun downloadFile(
    url: String,
    destination: java.io.File
) {

    val connection =
        java.net.URL(url).openConnection()

    connection.getInputStream().use { input ->

        destination.outputStream().use { output ->

            input.copyTo(output)

        }

    }

}

      
    /**
     * Returns the model currently being downloaded,
     * or null if no download is active.
     */
    fun activeDownload(): PiperModel? {

        return activeDownload

    }

    /**
     * Indicates whether a download
     * is currently in progress.
     */
    fun isDownloading(): Boolean {

        return activeDownload != null

    }

}
