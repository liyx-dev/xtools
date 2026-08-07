package com.liyx.xtools.core.voice

import com.liyx.xtools.core.providers.PiperBinaryDetector
import com.liyx.xtools.core.providers.PiperCommandBuilder
import com.liyx.xtools.core.providers.PiperModelDetector
import com.liyx.xtools.core.providers.PiperModelManager
import com.liyx.xtools.core.providers.PiperProcessRunner
import com.liyx.xtools.core.providers.PiperRuntime
import com.liyx.xtools.core.providers.PiperRuntimeManager
import com.liyx.xtools.core.providers.PiperRuntimeValidator


class PiperEngine(

    private val runtime: PiperRuntime = PiperRuntime(
        binaryPath = "",
        modelsDirectory = "",
        cacheDirectory = "",
        tempDirectory = ""
    )

) : VoiceEngine {

    private val runtimeManager =
        PiperRuntimeManager(runtime)

    private val binaryDetector =
        PiperBinaryDetector(runtime)

    private val modelDetector =
        PiperModelDetector(runtime)

    private val validator =
        PiperRuntimeValidator(
            runtimeManager,
            binaryDetector,
            modelDetector
        )


    private val commandBuilder =
        PiperCommandBuilder(runtime)

    private val processRunner =
        PiperProcessRunner()

    private val modelManager =
    PiperModelManager(modelDetector)

    private var selectedVoice: String? = null

    private var speed = 1f

    override fun initialize() {

        runtimeManager.prepareRuntime()

    }

   override fun getAvailableVoices(): List<VoiceInfo> {

    return emptyList()

}

override fun applyConfig(

    config: VoiceConfig

) {

    setVoice(config.voiceId)

    setSpeed(config.speed)

    setPitch(config.pitch)

}

    override fun setVoice(voice: String) {

        selectedVoice = voice

    }

    override fun setSpeed(speed: Float) {

        this.speed = speed

    }

    override fun setPitch(pitch: Float) {

        // Piper models generally ignore pitch.

    }

    override fun generate(text: String) {

        // Xtools uses generateToFile()

    }

    override fun generateToFile(

        text: String,

        outputPath: String

    ): Boolean {

        runtimeManager.prepareRuntime()

        val model =

            selectedVoice
                ?.let {

                    modelManager.getModelById(it)
                        ?: modelManager.getModelByName(it)

                }

                ?: modelManager
                    .downloadedModels()
                    .firstOrNull()

                ?: return false

        if (!validator.validate(model)) {

            return false

        }

        val command = commandBuilder.build(

            model,

            outputPath

        )

        return processRunner.run(command)

    }

    override fun stop() {

        // Process cancellation will be added later.

    }

    override fun release() {

        // Nothing to release yet.

    }



}
