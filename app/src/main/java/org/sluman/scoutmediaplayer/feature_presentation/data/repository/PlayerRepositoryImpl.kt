package org.sluman.scoutmediaplayer.feature_presentation.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import org.sluman.scoutmediaplayer.feature_player.PlaybackState
import org.sluman.scoutmediaplayer.feature_player.Player
import org.sluman.scoutmediaplayer.feature_presentation.data.PlayerState
import org.sluman.scoutmediaplayer.feature_presentation.domain.model.MediaItem
import org.sluman.scoutmediaplayer.feature_presentation.domain.repository.PlayerRepository
import org.sluman.scoutmediaplayer.feature_presentation.ui.viewmodels.ShuffleType


class PlayerRepositoryImpl(private val player: Player) : PlayerRepository {
    private var nowPlaying: MediaItem? = null
    private var shuffleType: ShuffleType = ShuffleType.REPEAT

    private val _playerState = MutableStateFlow(PlayerState())
    override val playerState: StateFlow<PlayerState> = _playerState

    init {
//        _playerState.update {
//            PlayerState(
//                isPlaying = false,
//                isEnded = true,
//                nowPlayingItem = MediaItem(
//                    id = 0,
//                    title = "Claire de Lune",
//                    artist = "Claude Debussy",
//                    uri = "Clair_de_Lune.mp3",
//                    trackLength = 312003
//                )
//            )
//        }
        player.setCallback {
            when (it) {
                PlaybackState.COMPLETED -> isCompleted()
                PlaybackState.STARTED -> isPlaying()
                PlaybackState.ERROR -> isError()
            }
        }
    }

    override fun play(item: MediaItem) {
        nowPlaying = item
        _playerState.update {
            PlayerState(
                isPlaying = true,
                isEnded = false,
                nowPlayingItem = nowPlaying
            )
        }
        player.play(item)
    }

    override fun pause() {
        player.pause()
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

    override fun isPlaying() {
        _playerState.update {
            PlayerState(
                isPlaying = true,
                isEnded = false,
                nowPlayingItem = nowPlaying
            )
        }
    }

    override fun isCompleted() {
        _playerState.update {
            PlayerState(
                isPlaying = false,
                isEnded = true,
                nowPlayingItem = nowPlaying
            )
        }
    }

    override fun isError() {
        _playerState.update {
            PlayerState(
                isPlaying = false,
                isEnded = true,
                nowPlayingItem = nowPlaying
            )
        }
    }
}