package com.liyx.xtools.core.voice

import com.liyx.xtools.core.providers.PiperBinaryDetector
import com.liyx.xtools.core.providers.PiperCommandBuilder
import com.liyx.xtools.core.providers.PiperModelDetector
import com.liyx.xtools.core.providers.PiperModelManager
import com.liyx.xtools.core.providers.PiperProcessRunner
import com.liyx.xtools.core.providers.PiperRuntime
import com.liyx.xtools.core.providers.PiperRuntimeManager
import com.liyx.xtools.core.providers.PiperRuntimeValidator

import com.liyx.xtools.core.inference.LiyXInferenceEngine
import com.liyx.xtools.core.inference.LiyXModelLoader
import com.liyx.xtools.core.inference.LiyXModelVerifier
import com.liyx.xtools.core.inference.LiyXSessionManager
import com.liyx.xtools.core.providers.PiperSessionProvider
import com.liyx.xtools.core.providers.PiperInferenceEngine
import com.liyx.xtools.core.providers.PiperDownloadManager
import android.content.Context
import com.liyx.xtools.core.utils.DebugLogger


class PiperEngine(

    private val context: Context,

    private val runtime: PiperRuntime = PiperRuntime(
        binaryPath = "",
        modelsDirectory = "",
        cacheDirectory = "",
        tempDirectory = ""
    ),
private val logger: (String) -> Unit = {}

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
    PiperProcessRunner(logger)

private val inferenceEngine =
    LiyXInferenceEngine()

private val modelLoader =
    LiyXModelLoader(inferenceEngine)

private val modelVerifier =
    LiyXModelVerifier()

private val sessionManager =
    LiyXSessionManager(modelLoader)

private val sessionProvider =
    PiperSessionProvider(
        runtime,
        modelVerifier,
        sessionManager
    )

private val piperInferenceEngine =
    PiperInferenceEngine(sessionProvider)

   private val downloader =
    PiperDownloadManager(runtime)

private val modelManager =
    PiperModelManager(
        modelDetector,
        downloader
    )

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

    logger("PiperEngine.generateToFile() called")

    logger("Piper text length = ${text.length}")

    logger("Piper requested output = $outputPath")

    runtimeManager.prepareRuntime()

    logger(
        "Piper binary = ${runtime.binaryPath}"
    )

    logger(
        "Piper models directory = ${runtime.modelsDirectory}"
    )

    logger(
        "Piper binary exists = " +
            binaryDetector.exists()
    )

    logger(
        "Piper binary executable = " +
            binaryDetector.isExecutable()
    )

    val model =

        selectedVoice
            ?.let {

                logger(
                    "Looking for selected Piper voice = $it"
                )

                modelManager.getModelById(it)
                    ?: modelManager.getModelByName(it)

            }

            ?: modelManager
                .downloadedModels()
                .firstOrNull()

            ?: run {

                logger(
                    "PIPER ERROR: No downloaded model found"
                )

                return false
            }

    logger(
        "Piper model selected = ${model.id}"
    )

    logger(
        "Piper model name = ${model.name}"
    )

    logger(
        "Piper model installed = " +
            modelDetector.isInstalled(model)
    )

    if (!validator.validate(model)) {

        logger(
            "PIPER ERROR: Runtime validation failed"
        )

        return false
    }

    logger(
        "Piper runtime validation PASSED"
    )

    val command =
        commandBuilder.build(
            model,
            outputPath
        )

    logger(
        "Piper command prepared"
    )

    return processRunner.run(
        command,
        text
    )
}

      
           
    override fun stop() {

        // Process cancellation will be added later.

    }

    override fun release() {

        // Nothing to release yet.

    }



}
