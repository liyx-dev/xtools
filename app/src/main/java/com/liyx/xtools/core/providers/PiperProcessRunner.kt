package com.liyx.xtools.core.providers

import java.io.File

class PiperProcessRunner(
    private val logger: (String) -> Unit = {}
) {

    fun run(
        command: List<String>,
        text: String
    ): Boolean {

        return try {

            logger("=================================")
            logger("PIPER PROCESS START")
            logger("=================================")

            logger("Piper command:")
            logger(command.joinToString(" "))

            logger("Piper text length = ${text.length}")

            val outputPath =
                command
                    .windowed(2)
                    .firstOrNull {
                        it[0] == "--output_file"
                    }
                    ?.getOrNull(1)

            if (outputPath != null) {
                logger("Piper output file = $outputPath")

                val parent =
                    File(outputPath).parentFile

                if (parent != null) {
                    parent.mkdirs()
                    logger(
                        "Output directory exists = ${parent.exists()}"
                    )
                }
            }

            val process = ProcessBuilder(command)
                .redirectErrorStream(true)
                .start()

            logger("Piper process started successfully")

            process.outputStream
                .bufferedWriter()
                .use { writer ->

                    writer.write(text)
                    writer.newLine()
                    writer.flush()
                }

            logger("Piper input sent")

            val processOutput =
                process.inputStream
                    .bufferedReader()
                    .use { reader ->
                        reader.readText()
                    }

            val exitCode = process.waitFor()

            if (processOutput.isNotBlank()) {

                logger("Piper console output:")
                logger(processOutput.trim())

            } else {

                logger("Piper console output: <empty>")

            }

            logger("Piper exit code = $exitCode")

            if (outputPath != null) {

                val outputFile =
                    File(outputPath)

                logger(
                    "Piper output exists = ${outputFile.exists()}"
                )

                logger(
                    "Piper output size = ${outputFile.length()} bytes"
                )
            }

            if (exitCode != 0) {

                logger(
                    "PIPER FAILED: exit code $exitCode"
                )

                return false
            }

            logger("PIPER PROCESS SUCCESS")

            true

        } catch (e: Exception) {

            logger("PIPER PROCESS EXCEPTION")
            logger(e.stackTraceToString())

            false
        }
    }
}
