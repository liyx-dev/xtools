package com.liyx.xtools.core.providers

data class PiperModel(

    val id: String,

    val name: String,

    val language: String,

    val modelUrl: String,
val configUrl: String,

    val downloaded: Boolean

)


class PiperModelManager(

    private val catalog: PiperCatalog = PiperCatalog()

) {

  
    fun getModels(): List<PiperModel> {

    return catalog.getModels()

}

    fun downloadedModels(): List<PiperModel> {

    return getModels().filter  {

        it.downloaded

    }

}

fun downloadableModels(): List<PiperModel> {

    return getModels().filter {

        it.modelUrl.isNotBlank()

    }

}


    fun getModelById(

        id: String

    ): PiperModel? {

        return getModels().firstOrNull  {

            it.id == id

        }

    }

    fun getModelByName(

        name: String

    ): PiperModel? {

        return getModels().firstOrNull  {

            it.name == name

        }

    }

}
