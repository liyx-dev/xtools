package com.liyx.xtools.core.providers

class VoiceProviderRegistry(

    providers: List<VoiceProvider> = emptyList()

) {

    private val providers = providers.toMutableList()

    fun register(

        provider: VoiceProvider

    ) {

        providers.removeAll {

            it.id == provider.id

        }

        providers.add(provider)

    }

    fun getProviders(): List<VoiceProvider> {

        return providers.toList()

    }

    fun getProvider(

        id: String

    ): VoiceProvider? {

        return providers.firstOrNull {

            it.id == id

        }

    }

    fun availableProviders(): List<VoiceProvider> {

        return providers.filter {

            it.isAvailable()

        }

    }

}
