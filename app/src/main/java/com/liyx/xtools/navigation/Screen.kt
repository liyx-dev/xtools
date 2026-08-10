package com.liyx.xtools.navigation

sealed class Screen(val route: String) {

    data object Home : Screen("home")

    data object VoiceStudio : Screen("voice_studio")

    data object AudioLibrary : Screen("audio_library")
data object VoiceLibrary : Screen("voice_library")

data object PiperModelStore : Screen("piper_model_store")
}
