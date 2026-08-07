package com.liyx.xtools.core.providers

import com.liyx.xtools.core.download.DownloadManager
import com.liyx.xtools.core.download.DownloadRequest
import java.io.File

class PiperDownloadManager(

    private val runtime: PiperRuntime

) {

    private val downloader = DownloadManager()

    fun download(

        model: PiperModel

    ): Boolean {

        val modelsDir = File(runtime.modelsDirectory)

        if (!modelsDir.exists()) {

            modelsDir.mkdirs()

        }

        val modelFile = File(

            modelsDir,

            "${model.id}.onnx"

        )

        val configFile = File(

            modelsDir,

            "${model.id}.onnx.json"

        )

        val modelResult = downloader.download(

            DownloadRequest(

                url = model.modelUrl,

                outputFile = modelFile.absolutePath

            )

        )

        if (!modelResult.success) {

            return false

        }

        val configResult = downloader.download(

            DownloadRequest(

                url = model.configUrl,

                outputFile = configFile.absolutePath

            )

        )

        return configResult.success

    }

}
