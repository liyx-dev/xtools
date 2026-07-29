package com.liyx.xtools.core.providers

interface VoiceProvider {

    val id: String

    val displayName: String

    val isOffline: Boolean

    fun isAvailable(): Boolean

}
