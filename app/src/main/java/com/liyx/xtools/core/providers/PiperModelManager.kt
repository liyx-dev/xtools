package com.liyx.xtools.core.providers

class PiperModelManager(

    private val detector: PiperModelDetector,
    private val downloader: PiperDownloadManager

) {

    private val catalog = PiperCatalog()

    fun getModels(): List<PiperModel> {

        return catalog.getModels().map { model ->

            model.copy(
                downloaded = detector.isInstalled(model)
            )

        }

    }

    fun downloadedModels(): List<PiperModel> {

        return getModels().filter {
            it.downloaded
        }

    }

    fun downloadableModels(): List<PiperModel> {

        return getModels().filter {

            !it.downloaded &&
            it.modelUrl.isNotBlank() &&
            it.configUrl.isNotBlank()

        }

    }

    fun getModelById(id: String): PiperModel? {

        return getModels().firstOrNull {
            it.id == id
        }

    }

    fun getModelByName(name: String): PiperModel? {

        return getModels().firstOrNull {
            it.name == name
        }

    }

    fun isInstalled(id: String): Boolean {

        return getModelById(id)?.downloaded == true

    }

    fun download(id: String): Boolean {

        val model = getModelById(id)
            ?: return false

        if (model.downloaded) {
            return true
        }

        return downloader.download(model)

    }

    fun refresh(): List<PiperModel> {

        return getModels()

    }

}
