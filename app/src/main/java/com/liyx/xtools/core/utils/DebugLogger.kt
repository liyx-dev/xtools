package com.liyx.xtools.core.utils

import android.content.Context
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DebugLogger {

    private const val FILE_NAME = "xtools_debug.log"

    private fun file(context: Context): File {
        return File(context.filesDir, FILE_NAME)
    }

    fun clear(context: Context) {
        file(context).writeText("")
    }

    fun log(
        context: Context,
        message: String
    ) {
        val time = SimpleDateFormat(
            "HH:mm:ss",
            Locale.getDefault()
        ).format(Date())

        file(context).appendText(
            "[$time] $message\n"
        )
    }

    fun read(context: Context): String {

        val f = file(context)

        return if (f.exists()) {

            f.readText()

        } else {

            "No logs yet."

        }

    }
}
