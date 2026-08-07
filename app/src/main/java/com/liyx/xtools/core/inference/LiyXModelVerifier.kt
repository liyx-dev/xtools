package com.liyx.xtools.core.inference

import java.io.File

class LiyXModelVerifier {

    fun exists(path: String): Boolean {
        return File(path).exists()
    }

    fun isReadable(path: String): Boolean {
        val file = File(path)
        return file.exists() && file.canRead()
    }

    fun isValidExtension(path: String): Boolean {
        return path.endsWith(".onnx")
    }

    fun verify(path: String): Boolean {
        return exists(path) &&
               isReadable(path) &&
               isValidExtension(path)
    }
}
