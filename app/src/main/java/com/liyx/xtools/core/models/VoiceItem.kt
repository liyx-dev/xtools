package com.liyx.xtools.core.models

data class VoiceItem(

    val id: String,

    val name: String,

    val locale: String,

    val provider: String,

    val quality: String,

    val offline: Boolean,

    val premium: Boolean = false

)
