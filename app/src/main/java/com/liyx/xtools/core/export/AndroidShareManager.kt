package com.liyx.xtools.core.export

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import java.io.File

class AndroidShareManager(

    private val context: Context

) {

    fun share(audio: ExportAudio) {

        val file = File(audio.filePath)

        if (!file.exists()) return

        val uri = FileProvider.getUriForFile(

            context,

            context.packageName + ".provider",

            file

        )

        val intent = Intent(Intent.ACTION_SEND).apply {

            type = audio.mimeType

            putExtra(Intent.EXTRA_STREAM, uri)

            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

        }

        context.startActivity(

            Intent.createChooser(

                intent,

                "Share Audio"

            )

        )

    }

}
