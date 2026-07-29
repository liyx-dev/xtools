package com.liyx.xtools.core.providers

data class PiperModel(

    val id: String,

    val name: String,

    val language: String,

    val downloaded: Boolean

)

class PiperModelManager {

    private val models = listOf(

        PiperModel(

            id = "en_US-lessac-medium",

            name = "English (Lessac)",

            language = "English",

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

}
