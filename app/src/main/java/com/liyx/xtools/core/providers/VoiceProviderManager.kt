package com.liyx.xtools.core.providers

class VoiceProviderManager(

    private val registry: VoiceProviderRegistry

) {

    private var currentProviderId = "android"

    fun setCurrentProvider(

        id: String

    ) {

        if (

            registry.getProvider(id) != null

        ) {

            currentProviderId = id

        }

    }

    fun getCurrentProvider(): VoiceProvider? {

        return registry.getProvider(

            currentProviderId

        )

    }

    fun getCurrentProviderId(): String {

        return currentProviderId

    }

    fun getAvailableProviders(): List<VoiceProvider> {

        return registry.availableProviders()

    }

}
