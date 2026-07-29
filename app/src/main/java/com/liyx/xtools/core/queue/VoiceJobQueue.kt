package com.liyx.xtools.core.queue

import com.liyx.xtools.core.jobs.VoiceJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class VoiceJobQueue {

    private val jobs = mutableListOf<VoiceJob>()

    /**
     * Prevents multiple jobs
     * from processing simultaneously.
     */
    private var processing = false

    private val _queue =
        MutableStateFlow<List<VoiceJob>>(emptyList())

    val queue: StateFlow<List<VoiceJob>> =
        _queue.asStateFlow()

    fun enqueue(job: VoiceJob) {

        jobs.add(job)

        _queue.value = jobs.toList()

    }

    fun dequeue(): VoiceJob? {

        if (jobs.isEmpty()) return null

        val job = jobs.removeAt(0)

        _queue.value = jobs.toList()

        return job

    }

    fun peek(): VoiceJob? {

        return jobs.firstOrNull()

    }

    fun size(): Int {

        return jobs.size

    }

    fun clear() {

        jobs.clear()

        _queue.value = emptyList()

        processing = false

    }

    fun isProcessing(): Boolean {

        return processing

    }

    fun startProcessing() {

        processing = true

    }

    fun finishProcessing() {

        processing = false

    }

}
