package com.liyx.xtools.core.storage

import android.content.Context
import java.io.File

class AudioStorageManager(

    private val context: Context

) {

    private val rootDirectory: File by lazy {

        File(
            context.filesDir,
            "voice_projects"
        ).apply {

            if (!exists()) {
                mkdirs()
            }

        }

    }

    fun getProjectDirectory(

        projectTitle: String

    ): File {

        val safeName = projectTitle

            .trim()

            .ifBlank { "Untitled Project" }

            .replace(
                Regex("[^A-Za-z0-9_-]"),
                "_"
            )

        return File(
            rootDirectory,
            safeName
        ).apply {

            if (!exists()) {
                mkdirs()
            }

        }

    }

    fun getOutputDirectory(

        projectTitle: String

    ): String {

        return getProjectDirectory(
            projectTitle
        ).absolutePath
    }


fun getVoiceProjectsDirectory(): File {

    return rootDirectory

}

fun getAllAudioFiles(): List<File> {

    return rootDirectory

        .walkTopDown()

       .filter {

    it.isFile &&

    !it.name.startsWith("chunk_") &&

    (

        it.extension.equals("wav", true) ||

        it.extension.equals("mp3", true)

    )

}

        .toList()

}


}
