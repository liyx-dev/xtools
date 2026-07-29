package com.liyx.xtools.core.player

import android.content.Context
import android.media.MediaPlayer

/**
 * Handles playback of generated audio.
 */
class AudioPlayer(

    private val context: Context

) {

    private var mediaPlayer: MediaPlayer? = null

    fun play(filePath: String) {

        stop()

        mediaPlayer = MediaPlayer().apply {

            setDataSource(filePath)

            prepare()

            start()

        }

    }

    fun pause() {

        mediaPlayer?.takeIf {

            it.isPlaying

        }?.pause()

    }

    fun resume() {

        mediaPlayer?.start()

    }

    fun stop() {

        mediaPlayer?.release()

        mediaPlayer = null

    }

    fun isPlaying(): Boolean {

        return mediaPlayer?.isPlaying ?: false

    }

fun getCurrentPosition(): Int {

    return mediaPlayer?.currentPosition ?: 0

}

fun getDuration(): Int {

    return mediaPlayer?.duration ?: 0

}

fun seekTo(position: Int) {

    mediaPlayer?.seekTo(position)

}

    fun release() {

        stop()

    }

}
