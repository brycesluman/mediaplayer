package org.sluman.playermodule

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.IBinder
import android.os.PowerManager
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class PlayerService : Service(),
    MediaPlayer.OnPreparedListener,
    MediaPlayer.OnErrorListener,
    MediaPlayer.OnCompletionListener {
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate() {
        super.onCreate()
        // need foreground notification here. would build up a media version with more time
        val channelId = "scout_notification"
        val channel = NotificationChannel(
            channelId,
            "Scout Media Player",
            NotificationManager.IMPORTANCE_DEFAULT
        )

        (getSystemService(NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(
            channel
        )

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("")
            .setContentText("").build()

        startForeground(1, notification)
    }

    private fun initMediaPlayer(): MediaPlayer {
        mediaPlayer = MediaPlayer().apply {
            setWakeMode(applicationContext, PowerManager.PARTIAL_WAKE_LOCK)
        }
        mediaPlayer?.setOnErrorListener(this)

        return mediaPlayer as MediaPlayer
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val action: PlaybackAction? = intent.getEnumExtra<PlaybackAction>()
        when (action) {
            PlaybackAction.ACTION_PLAY -> {
                if (mediaPlayer?.isPlaying == true) {
                    mediaPlayer?.stop()
                }
                intent.getStringExtra("URI")?.let {
                    val afd = assets.openFd(it)
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
                        setOnPreparedListener(this@PlayerService)
                        setOnCompletionListener(this@PlayerService)
                        prepareAsync()
                    }// prepare async to not block main thread
                }

            }

            PlaybackAction.ACTION_PAUSE -> {
                if (mediaPlayer?.isPlaying == true) {
                    mediaPlayer?.pause()
                }
            }

            PlaybackAction.ACTION_STOP -> {
                if (mediaPlayer?.isPlaying == true) {
                    mediaPlayer?.stop()
                }
            }

            else -> {

            }
        }

        return START_STICKY_COMPATIBILITY
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    /** Called when MediaPlayer is ready */
    override fun onPrepared(mediaPlayer: MediaPlayer) {
        Log.d("PlayerService", "Action: PLAYBACK_STARTING")
        val intent = Intent()
        intent.setAction(PLAYBACK_STARTING)
        LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(intent)
        mediaPlayer.start()
    }

    override fun onError(mediaPlayer: MediaPlayer?, p1: Int, p2: Int): Boolean {
        Log.d("PlayerService", "Action: ERROR_OCCURRED")
        val intent = Intent()
        intent.setAction(ERROR_OCCURRED)
        LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(intent)
        return false
    }

    override fun onCompletion(mediaPlayer: MediaPlayer) {
        Log.d("PlayerService", "Action: PLAYBACK_ENDED")
        val intent = Intent()
        intent.setAction(PLAYBACK_ENDED)
        LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
    companion object {
        const val PLAYBACK_ENDED = "org.sluman.playerevents.PLAYBACK_ENDED"
        const val ERROR_OCCURRED = "org.sluman.playerevents.ERROR_OCCURRED"
        const val PLAYBACK_STARTING = "org.sluman.playerevents.PLAYBACK_STARTING"
    }
}

enum class PlaybackAction {
    ACTION_PLAY,
    ACTION_PAUSE,
    ACTION_STOP
}


inline fun <reified T : Enum<T>> Intent.putExtra(victim: T): Intent =
    putExtra(T::class.java.name, victim.ordinal)

inline fun <reified T : Enum<T>> Intent.getEnumExtra(): T? =
    getIntExtra(T::class.java.name, -1)
        .takeUnless { it == -1 }
        ?.let { T::class.java.enumConstants?.get(it) }