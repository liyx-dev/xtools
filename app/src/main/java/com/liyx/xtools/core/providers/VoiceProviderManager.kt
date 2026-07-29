package com.liyx.xtools.core.providers

class VoiceProviderManager(

    private val registry: VoiceProviderRegistry

) {

    private var currentProviderId: String? = null

    fun setCurrentProvider(id: String) {

        currentProviderId = id

    }

    fun getCurrentProvider(): VoiceProvider? {

        return currentProviderId
            ?.let { registry.getProvider(it) }
            ?: registry.availableProviders().firstOrNull()

    }

    fun getAvailableProviders(): List<VoiceProvider> {

        return registry.availableProviders()

    }

}
