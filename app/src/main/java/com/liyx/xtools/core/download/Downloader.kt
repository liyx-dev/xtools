package com.liyx.xtools.core.download

interface Downloader {

    fun download(

        request: DownloadRequest

    ): DownloadResult

}
