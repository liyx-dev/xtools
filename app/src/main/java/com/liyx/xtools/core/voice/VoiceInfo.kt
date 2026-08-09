package com.liyx.xtools.core.voice

data class VoiceInfo(

    val id: String,

    val name: String,

    val locale: String,

    val provider: String,

    val networkRequired: Boolean,

    val quality: String,

    val gender: String? = null,

    val isOffline: Boolean = true,

    val isInstalled: Boolean = false

)
