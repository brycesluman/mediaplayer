package org.sluman.scoutmediaplayer.feature_presentation.domain.repository

import kotlinx.coroutines.flow.StateFlow
import org.sluman.scoutmediaplayer.feature_presentation.data.PlayerState
import org.sluman.scoutmediaplayer.feature_presentation.domain.model.MediaItem
import org.sluman.scoutmediaplayer.feature_presentation.ui.viewmodels.ShuffleType

interface PlayerRepository {
    val playerState: StateFlow<PlayerState>
    fun play(item: MediaItem)
    fun pause()
    fun toggleShuffleMode(mode: ShuffleType)
    fun getShuffleMode(): ShuffleType
    fun getNowPlayingItem(): MediaItem?
    fun isPlaying()
    fun isCompleted()
    fun isError()
}