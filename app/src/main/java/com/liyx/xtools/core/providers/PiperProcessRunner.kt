package com.liyx.xtools.core.providers

class PiperProcessRunner {

    fun run(
        command: List<String>,
        text: String
    ): Boolean {

        return try {

            val process = ProcessBuilder(command)
                .redirectErrorStream(true)
                .start()

            process.outputStream.bufferedWriter().use { writer ->

                writer.write(text)
                writer.newLine()
                writer.flush()

            }

            process.waitFor() == 0

        } catch (e: Exception) {

            false

        }

    }

}
