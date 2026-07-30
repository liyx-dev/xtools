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
     * NOTE:
     * Real networking and file downloading
     * will be implemented in the next phase.
     */
    fun downloadModel(

        model: PiperModel

    ): PiperDownloadStatus {

        // Already installed.
        if (model.downloaded) {

            return PiperDownloadStatus.ALREADY_DOWNLOADED

        }

        // Mark this model as the current download.
        activeDownload = model

        /*
         * TODO
         *
         * Download the model.
         * Verify the files.
         * Save into the models directory.
         * Update installation status.
         */

        // Download finished (or failed).
        activeDownload = null

        return PiperDownloadStatus.FAILED

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
