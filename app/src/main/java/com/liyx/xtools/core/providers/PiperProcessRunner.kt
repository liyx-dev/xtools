package com.liyx.xtools.core.providers

import android.util.Log
import java.io.File

class PiperProcessRunner {

    companion object {
        private const val TAG = "XTOOLS_PIPER"
    }

    fun run(
        command: List<String>,
        text: String
    ): Boolean {

        return try {

            Log.d(TAG, "========== PIPER START ==========")
            Log.d(TAG, "Command: ${command.joinToString(" ")}")
            Log.d(TAG, "Text length: ${text.length}")

            val outputFile = command
                .windowed(2)
                .firstOrNull {
                    it[0] == "--output_file"
                }
                ?.get(1)

            Log.d(TAG, "Expected output: $outputFile")

            if (outputFile != null) {
                val file = File(outputFile)
                Log.d(TAG, "Output parent: ${file.parent}")
            }

            val process = ProcessBuilder(command)
                .redirectErrorStream(true)
                .start()

            Log.d(TAG, "Piper process started")

            process.outputStream
                .bufferedWriter()
                .use { writer ->
                    writer.write(text)
                    writer.flush()
                }

            Log.d(TAG, "Text sent to Piper")

            val output = process.inputStream
                .bufferedReader()
                .readText()

            if (output.isNotBlank()) {
                Log.d(TAG, "Piper output: $output")
            } else {
                Log.d(TAG, "Piper produced no console output")
            }

            val exitCode = process.waitFor()

            Log.d(TAG, "Piper exit code: $exitCode")

            if (outputFile != null) {

                val file = File(outputFile)

                Log.d(
                    TAG,
                    "Output exists: ${file.exists()}"
                )

                Log.d(
                    TAG,
                    "Output size: ${file.length()} bytes"
                )
            }

            Log.d(TAG, "========== PIPER END ==========")

            exitCode == 0

        } catch (e: Exception) {

            Log.e(
                TAG,
                "Piper execution exception",
                e
            )

            false
        }
    }
}
