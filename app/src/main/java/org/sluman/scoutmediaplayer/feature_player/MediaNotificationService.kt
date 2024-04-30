package org.sluman.scoutmediaplayer.feature_player

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.annotation.OptIn
import androidx.core.app.NotificationCompat
import androidx.media3.common.util.UnstableApi
import androidx.media3.session.MediaStyleNotificationHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.sluman.scoutmediaplayer.R
import org.sluman.scoutmediaplayer.feature_presentation.data.PlayerState
import org.sluman.scoutmediaplayer.feature_presentation.domain.repository.PlayerRepository
import javax.inject.Inject

@AndroidEntryPoint
class MediaNotificationService: Service() {
    @Inject
    lateinit var repository: PlayerRepository
    override fun onCreate() {
        super.onCreate()
        setNotification(null)
        GlobalScope.launch {
            repository.playerState.collectLatest {
                setNotification(it)
                Log.d("MediaNotificationService", "current state: $it")
            }
        }
    }
    @OptIn(UnstableApi::class)
    private fun setNotification(state: PlayerState?) {
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
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(state?.nowPlayingItem?.title?:"")
            .setOnlyAlertOnce(true)
            .setContentText(state?.nowPlayingItem?.artist?:"").build()

        startForeground(1, notification)
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}