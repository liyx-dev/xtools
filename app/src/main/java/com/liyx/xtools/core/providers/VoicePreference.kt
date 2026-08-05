package com.liyx.xtools.core.providers

import android.content.Context

class VoicePreference(context: Context) {

    private val prefs =
        context.getSharedPreferences(
            "xtools_voice_preferences",
            Context.MODE_PRIVATE
        )

    fun saveVoice(id: String) {
        prefs.edit()
            .putString("selected_voice", id)
            .apply()
    }

    fun loadVoice(): String? {
        return prefs.getString("selected_voice", null)
    }
}
