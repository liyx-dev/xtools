package com.liyx.xtools.core.media.stream

import com.liyx.xtools.core.media.WavHeader
import java.io.File
import java.io.RandomAccessFile

class StreamingWavWriter(

    private val output: File

) {

    private var raf: RandomAccessFile? = null

    private var totalPCMBytes = 0

    private lateinit var header: WavHeader

    fun start(header: WavHeader) {

        this.header = header

        output.parentFile?.mkdirs()

        if (output.exists()) output.delete()

        raf = RandomAccessFile(output, "rw")

        repeat(44) {

            raf!!.write(0)

        }

    }

    fun appendPCM(bytes: ByteArray) {

        raf?.write(bytes)

        totalPCMBytes += bytes.size

    }

    fun finish() {

        raf?.seek(0)

        val finalHeader = header.copy(

            dataSize = totalPCMBytes

        )

        raf?.write(finalHeader.toByteArray())

        raf?.close()

    }

}
