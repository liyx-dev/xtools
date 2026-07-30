package com.liyx.xtools.core.providers

data class PiperModel(

    val id: String,

    val name: String,

    val language: String,

    val downloadUrl: String,

    val downloaded: Boolean

)



class PiperModelManager {

    private val models = listOf(

       PiperModel(

    id = "en_US-lessac-medium",

    name = "English (Lessac)",

    language = "English",

    downloadUrl = "",

    downloaded = false

)

    )

    fun getModels(): List<PiperModel> {

        return models

    }

    fun downloadedModels(): List<PiperModel> {

        return models.filter {

            it.downloaded

        }

    }

fun downloadableModels(): List<PiperModel> {

    return models.filter {

        it.downloadUrl.isNotBlank()

    }

}

    fun getModelById(

        id: String

    ): PiperModel? {

        return models.firstOrNull {

            it.id == id

        }

    }

    fun getModelByName(

        name: String

    ): PiperModel? {

        return models.firstOrNull {

            it.name == name

        }

    }

}
