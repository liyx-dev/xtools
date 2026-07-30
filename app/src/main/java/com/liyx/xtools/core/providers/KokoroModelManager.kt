package com.liyx.xtools.core.providers

data class KokoroModel(

    val id: String,

    val name: String,

    val language: String,

    val downloaded: Boolean = false

)

class KokoroModelManager {

    private val models = listOf(

        KokoroModel(

            id = "kokoro-en",

            name = "English",

            language = "en"

        ),

        KokoroModel(

            id = "kokoro-es",

            name = "Spanish",

            language = "es"

        ),

        KokoroModel(

            id = "kokoro-fr",

            name = "French",

            language = "fr"

        )

    )

    fun getModels(): List<KokoroModel> {

        return models

    }

    fun downloadedModels(): List<KokoroModel> {

        return models.filter {

            it.downloaded

        }

    }

}
