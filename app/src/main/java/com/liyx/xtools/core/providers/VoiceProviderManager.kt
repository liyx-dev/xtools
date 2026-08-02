package com.liyx.xtools.core.providers

import com.liyx.xtools.core.voice.VoiceEngine
import com.liyx.xtools.core.voice.VoiceInfo

class VoiceProviderManager(

    private val registry: VoiceProviderRegistry

) {

    private var currentProviderId = "android"

    fun setCurrentProvider(

        id: String

    ): Boolean {

        val provider = registry.getProvider(id)
            ?: return false

        currentProviderId = provider.id

        return true

    }

    fun getCurrentProvider(): VoiceProvider? {

        return registry.getProvider(currentProviderId)

    }

    fun getCurrentProviderId(): String {

        return currentProviderId

    }

    fun getCurrentEngine(): VoiceEngine? {

        return getCurrentProvider()?.engine

    }

    fun getAvailableProviders(): List<VoiceProvider> {

        return registry.availableProviders()

    }
fun getAllVoices(): List<VoiceInfo> {

    return registry

        .availableProviders()

        .flatMap {

            it.getVoices()

        }

}


}
