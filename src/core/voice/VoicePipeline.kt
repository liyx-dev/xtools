package com.liyx.xtools.core.voice

import com.liyx.xtools.core.jobs.VoiceJob
import com.liyx.xtools.core.models.ChunkStatus
import com.liyx.xtools.core.models.VoiceJobStatus

/**
 * VoicePipeline
 *
 * Coordinates the complete voice
 * generation workflow.
 */
class VoicePipeline(

    private val voiceEngine: VoiceEngine,

    private val audioMerger: AudioMerger

) {

    /**
     * Process one VoiceJob.
     *
     * @param outputDirectory Folder where chunk audio
     * files and final audio will be written.
     *
     * @return Path to the merged audio file,
     * or null if generation failed.
     */
    fun process(

        job: VoiceJob,

        outputDirectory: String

    ): String? {

        if (job.chunks.isEmpty()) {

            job.status = VoiceJobStatus.FAILED

            return null

        }

        job.status = VoiceJobStatus.PROCESSING

        job.startedAt = System.currentTimeMillis()

        val generatedFiles = mutableListOf<String>()

        job.chunks.forEachIndexed { index, chunk ->

            chunk.status = ChunkStatus.PROCESSING

            val chunkFile =

                "$outputDirectory/chunk_${index + 1}.wav"

            val success = voiceEngine.generateToFile(

                chunk.text,

                chunkFile

            )

            if (!success) {

                chunk.status = ChunkStatus.FAILED

                job.status = VoiceJobStatus.FAILED

                job.finishedAt = System.currentTimeMillis()

                return null

            }

            chunk.status = ChunkStatus.COMPLETED

            chunk.audioFile = chunkFile

            generatedFiles.add(chunkFile)

            job.processedCharacters += chunk.characterCount

            job.updateProgress()

        }

        val outputFile =

            "$outputDirectory/${job.title}.wav"

        val merged = audioMerger.merge(

            generatedFiles,

            outputFile

        )

        if (!merged) {

            job.status = VoiceJobStatus.FAILED

            job.finishedAt = System.currentTimeMillis()

            return null

        }

        job.outputFile = outputFile

        job.progress = 1f

        job.status = VoiceJobStatus.COMPLETED

        job.finishedAt = System.currentTimeMillis()

        return outputFile

    }

}
