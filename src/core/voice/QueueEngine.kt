package com.liyx.xtools.core.voice

import com.liyx.xtools.core.jobs.VoiceJob

/**
 * QueueEngine
 *
 * Manages VoiceJobs waiting
 * to be processed.
 */
class QueueEngine {

    private val queue = mutableListOf<VoiceJob>()

    /**
     * Add a new job.
     */
    fun add(job: VoiceJob) {

        queue.add(job)

    }

    /**
     * Add multiple jobs.
     */
    fun addAll(jobs: List<VoiceJob>) {

        queue.addAll(jobs)

    }

    /**
     * Get the next job.
     */
    fun next(): VoiceJob? {

        if (queue.isEmpty()) return null

        return queue.removeAt(0)

    }

    /**
     * Peek without removing.
     */
    fun peek(): VoiceJob? {

        return queue.firstOrNull()

    }

    /**
     * Remove everything.
     */
    fun clear() {

        queue.clear()

    }

    /**
     * Number of queued jobs.
     */
    fun size(): Int {

        return queue.size

    }

    /**
     * Is queue empty?
     */
    fun isEmpty(): Boolean {

        return queue.isEmpty()

    }

    /**
     * Current jobs.
     */
    fun jobs(): List<VoiceJob> {

        return queue.toList()

    }

}
