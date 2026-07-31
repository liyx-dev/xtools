package com.liyx.xtools.core.export

import android.content.Context
import android.os.Environment
import com.liyx.xtools.core.voice.AudioExporter
import java.io.File

class AndroidAudioExporter(

    private val context: Context

) : AudioExporter {

    override fun export(

        sourceFile: String,

        displayName: String

    ): Boolean {

        return try {

            val source = File(sourceFile)

            if (!source.exists()) {

                return false

            }

            val downloads =

                Environment.getExternalStoragePublicDirectory(

                    Environment.DIRECTORY_DOWNLOADS

                )

            if (!downloads.exists()) {

                downloads.mkdirs()

            }

            val destination =

                File(

                    downloads,

                    "$displayName.wav"

                )

            source.copyTo(

                destination,

                overwrite = true

            )

            true

        } catch (

            e: Exception

        ) {

            false

        }

    }

}
