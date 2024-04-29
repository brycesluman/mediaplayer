package org.sluman.scoutmediaplayer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.NavHostController
import dagger.hilt.android.AndroidEntryPoint
import org.sluman.scoutmediaplayer.domain.repository.PlayerRepository
import org.sluman.scoutmediaplayer.ui.ScoutApp
import org.sluman.scoutmediaplayer.ui.theme.ScoutTheme
import org.sluman.scoutmediaplayer.ui.viewmodels.PlayerViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var playerRepository: PlayerRepository
    private lateinit var receiver: PlayerEventReceiver
    class PlayerEventReceiver(private val playerRepository: PlayerRepository) : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            intent.action?.let { playerRepository.receivePlayerEvents(it) }
            Log.d("MainActivity", "Action: ${intent.action}" )
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        receiver = PlayerEventReceiver(playerRepository)
        enableEdgeToEdge()
        setContent {
            ScoutTheme {
                ScoutApp()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        LocalBroadcastManager.getInstance(applicationContext).registerReceiver(
            receiver,
            IntentFilter().apply {
            addAction("org.sluman.playerevents.PLAYBACK_STARTING")
            addAction("org.sluman.playerevents.ERROR_OCCURRED")
            addAction("org.sluman.playerevents.PLAYBACK_ENDED")
        })
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(applicationContext).unregisterReceiver(receiver)
    }
}