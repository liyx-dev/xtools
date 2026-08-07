package com.liyx.xtools.core.download

import java.io.File
import java.net.URL

class DownloadManager : Downloader {

    override fun download(
        request: DownloadRequest
    ): DownloadResult {

        return try {

            val destination = File(request.outputFile)

            destination.parentFile?.mkdirs()

            URL(request.url)
                .openStream()
                .use { input ->

                    destination.outputStream().use { output ->

                        input.copyTo(output)

                    }

                }

            DownloadResult(
                success = true,
                message = "Download completed."
            )

        } catch (e: Exception) {

            DownloadResult(
                success = false,
                message = e.message ?: "Download failed."
            )

        }

    }

}
