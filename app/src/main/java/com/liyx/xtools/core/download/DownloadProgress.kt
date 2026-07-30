package com.liyx.xtools.core.download

data class DownloadProgress(

    val downloadedBytes: Long,

    val totalBytes: Long

) {

    val progress: Float

        get() =

            if (totalBytes == 0L)

                0f

            else

                downloadedBytes.toFloat() /

                    totalBytes.toFloat()

}
