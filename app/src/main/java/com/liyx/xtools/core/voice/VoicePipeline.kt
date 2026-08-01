package com.liyx.xtools.core.voice

import java.io.File
import com.liyx.xtools.core.jobs.VoiceJob
import com.liyx.xtools.core.models.ChunkStatus
import com.liyx.xtools.core.models.VoiceJobStatus
import com.liyx.xtools.core.media.AudioMerger

/**
 * VoicePipeline
 *
 * Coordinates the complete voice
 * generation workflow.
 */
class VoicePipeline(

    private val voiceEngine: VoiceEngine,

    private val audioMerger: AudioMerger,

    private val onChunkCompleted: ((VoiceJob) -> Unit)? = null,

    private val logger: ((String) -> Unit)? = null

) {

    private fun log(message: String) {
        logger?.invoke(message)
    }

    /**
     * Process one VoiceJob.
     *
     * @param outputDirectory Folder where chunk audio
     * files and final audio will be written.
     *
     * @return Path to merged audio,
     * or null if generation failed.
     */
    fun process(

        job: VoiceJob,

        outputDirectory: String

    ): String? {

        log("VoicePipeline started")
        log("Project = ${job.title}")
        log("Chunks = ${job.totalChunks()}")

        if (job.chunks.isEmpty()) {

            log("Job contains no chunks")

            job.status = VoiceJobStatus.FAILED

            return null

        }

        job.status = VoiceJobStatus.PROCESSING
        job.startedAt = System.currentTimeMillis()

        val generatedFiles = mutableListOf<String>()

        job.chunks.forEachIndexed { index, chunk ->

            log("Processing chunk ${index + 1}/${job.totalChunks()}")

            chunk.status = ChunkStatus.PROCESSING

            val chunkFile =
                "$outputDirectory/chunk_${index + 1}.wav"

            log("Output file:")
            log(chunkFile)

            log("Calling generateToFile()")

            val success = voiceEngine.generateToFile(

                chunk.text,

                chunkFile

            )

            log("generateToFile returned = $success")

            if (!success) {

                log("Chunk generation FAILED")

                chunk.status = ChunkStatus.FAILED

                job.status = VoiceJobStatus.FAILED

                job.finishedAt = System.currentTimeMillis()

                return null

            }

            val file = File(chunkFile)

            log("Chunk file exists = ${file.exists()}")
            log("Chunk size = ${file.length()} bytes")

            chunk.status = ChunkStatus.COMPLETED
            chunk.audioFile = chunkFile

            generatedFiles.add(chunkFile)

            job.processedCharacters += chunk.characterCount
            job.updateProgress()

            onChunkCompleted?.invoke(job)

            log("Chunk ${index + 1} completed")
        }


              val outputFile =
            "$outputDirectory/${job.title}.wav"

        log("=================================")
        log("STARTING AUDIO MERGE")
        log("=================================")

        log("Output file:")
        log(outputFile)

        log("Generated chunk count = ${generatedFiles.size}")

        generatedFiles.forEachIndexed { index, file ->

            log("Chunk ${index + 1}")

            log(file)

        }

        val merged = audioMerger.merge(

            generatedFiles,

            outputFile

        )

        log("Merge completed")

        log("Merge result = $merged")

        if (!merged) {

            log("Audio merge FAILED")

            job.status = VoiceJobStatus.FAILED
            job.finishedAt = System.currentTimeMillis()

            return null

        }

        log("Checking merged file...")

        val mergedFile = File(outputFile)

        log("Merged file exists = ${mergedFile.exists()}")

        log("Merged file size = ${mergedFile.length()} bytes")

        if (!mergedFile.exists()) {

            log("ERROR: merged file does not exist")

            job.status = VoiceJobStatus.FAILED
            job.finishedAt = System.currentTimeMillis()

            return null

        }

        job.outputFile = outputFile

        job.progress = 1f

        job.status = VoiceJobStatus.COMPLETED

        job.finishedAt = System.currentTimeMillis()

        log("=================================")
        log("VOICE PIPELINE COMPLETED")
        log("=================================")

        return outputFile

   

    }

}
