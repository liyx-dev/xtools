package com.liyx.xtools.core.inference

import ai.onnxruntime.OrtSession

class LiyXSessionTester {

    fun isValid(

        session: OrtSession?

    ): Boolean {

        return try {

            session != null

        } catch (e: Exception) {

            false

        }

    }

}
