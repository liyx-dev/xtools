package com.liyx.xtools.core.providers

class PiperProcessRunner {

    fun run(

        command: List<String>

    ): Boolean {

        return try {

            val process = ProcessBuilder(command)

                .redirectErrorStream(true)

                .start()

            process.waitFor() == 0

        } catch (e: Exception) {

            false

        }

    }

}
