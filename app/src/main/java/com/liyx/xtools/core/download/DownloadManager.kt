package com.liyx.xtools.core.download

class DownloadManager : Downloader {

    override fun download(

        request: DownloadRequest

    ): DownloadResult {

        /*
         * Real HTTP downloading
         * will be implemented
         * in the next phase.
         */

        return DownloadResult(

            success = false,

            message =

                "Download engine not implemented."

        )

    }

}
