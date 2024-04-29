package org.sluman.scoutmediaplayer.data.repository

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import org.sluman.playermodule.PlaybackAction
import org.sluman.playermodule.PlayerService
import org.sluman.playermodule.PlayerService.Companion.PLAYBACK_ENDED
import org.sluman.playermodule.putExtra
import org.sluman.scoutmediaplayer.data.PlayerState
import org.sluman.scoutmediaplayer.domain.model.MediaItem
import org.sluman.scoutmediaplayer.domain.repository.PlayerRepository
import org.sluman.scoutmediaplayer.ui.viewmodels.ShuffleType


class PlayerRepositoryImpl(private val context: Context): PlayerRepository {
    private var nowPlaying: MediaItem? = null
    private var shuffleType: ShuffleType = ShuffleType.REPEAT

    private val _playerState = MutableStateFlow(PlayerState())
    override val playerState: StateFlow<PlayerState> = _playerState

    override fun play(item: MediaItem) {
        nowPlaying = item
        _playerState.update {
            PlayerState(
                isPlaying = true,
                isEnded = false,
                nowPlayingItem = nowPlaying
            )
        }
        val intent = Intent(context, PlayerService::class.java)
        intent.putExtra(PlaybackAction.ACTION_PLAY)
        intent.putExtra("URI", item.uri)
        ContextCompat.startForegroundService(context, intent)
    }

    override fun pause() {
        val intent = Intent(context, PlayerService::class.java)
        intent.putExtra(PlaybackAction.ACTION_PAUSE)
        ContextCompat.startForegroundService(context, intent)
    }

    override fun toggleShuffleMode(mode: ShuffleType) {
        shuffleType = mode
    }

    override fun getShuffleMode(): ShuffleType {
        return shuffleType
    }

    override fun getNowPlayingItem(): MediaItem? {
        return nowPlaying
    }

    override fun receivePlayerEvents(state: String) {
        _playerState.update {
            PlayerState(
                isPlaying = state != PLAYBACK_ENDED,
                isEnded = state == PLAYBACK_ENDED,
                nowPlayingItem = nowPlaying
            )
        }
    }
}