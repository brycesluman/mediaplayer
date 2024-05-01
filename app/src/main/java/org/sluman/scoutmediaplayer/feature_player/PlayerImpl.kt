package org.sluman.scoutmediaplayer.feature_player

import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.PowerManager
import androidx.core.content.ContextCompat
import org.sluman.scoutmediaplayer.feature_presentation.domain.model.MediaItem
import javax.inject.Inject


interface Player {
    fun play(item: MediaItem)
    fun pause()
    fun stop()
    fun setCallback(listener: (PlaybackState) -> Unit)
}

class PlayerImpl @Inject constructor(val context: Context) : Player,
    MediaPlayer.OnPreparedListener,
    MediaPlayer.OnErrorListener,
    MediaPlayer.OnCompletionListener {
    private var mediaPlayer: MediaPlayer? = null
    private var length = 0

    private var onResult: ((PlaybackState) -> Unit)? = null

    override fun setCallback(listener: (PlaybackState) -> Unit) {
        this.onResult = listener
    }

    private fun initMediaPlayer(): MediaPlayer {
        mediaPlayer = MediaPlayer().apply {
            setWakeMode(context, PowerManager.PARTIAL_WAKE_LOCK)
        }
        mediaPlayer?.setOnErrorListener(this)

        return mediaPlayer as MediaPlayer
    }

    override fun play(item: MediaItem) {
        ContextCompat.startForegroundService(
            context,
            Intent(context, MediaNotificationService::class.java)
        )
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.stop()
        }

        item.uri?.let {
            val afd = context.assets.openFd(it)
            if (mediaPlayer == null) {
                mediaPlayer = initMediaPlayer()// initialize it here
            }
            mediaPlayer?.apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
                reset()
                setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
                setOnPreparedListener(this@PlayerImpl)
                setOnCompletionListener(this@PlayerImpl)
                prepareAsync()
            }// prepare async to not block main thread
        }


    }

    override fun pause() {
        length = mediaPlayer!!.currentPosition
        if (mediaPlayer != null && mediaPlayer?.isPlaying == true) {
            mediaPlayer?.pause()
        }
    }

    override fun stop() {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.stop()
            length = 0
        }
    }

    /** Called when MediaPlayer is ready */
    override fun onPrepared(mediaPlayer: MediaPlayer) {
        onResult?.let { it(PlaybackState.STARTED) }
        if( length>0 ){
            mediaPlayer.seekTo(length)
            length = 0
        }
        mediaPlayer.start()
    }

    override fun onError(mediaPlayer: MediaPlayer?, p1: Int, p2: Int): Boolean {
        length = 0
        onResult?.let { it(PlaybackState.ERROR) }
        return false
    }

    override fun onCompletion(mediaPlayer: MediaPlayer) {
        length = 0
        onResult?.let { it(PlaybackState.COMPLETED) }
    }

    fun onDestroy() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

}

enum class PlaybackState {
    COMPLETED,
    ERROR,
    STARTED
}
