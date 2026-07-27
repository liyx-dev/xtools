package com.liyx.xtools.core.voice

/**
 * QueueEngine
 *
 * Processes text chunks one after another.
 */
class QueueEngine {

    private val queue = mutableListOf<String>()

    fun add(chunks: List<String>) {

        queue.addAll(chunks)

    }

    fun next(): String? {

        if (queue.isEmpty()) return null

        return queue.removeAt(0)

    }

    fun clear() {

        queue.clear()

    }

    fun size(): Int {

        return queue.size

    }

}
