package org.sluman.scoutmediaplayer.domain.repository

import kotlinx.coroutines.flow.StateFlow
import org.sluman.scoutmediaplayer.data.PlayerState
import org.sluman.scoutmediaplayer.domain.model.MediaItem
import org.sluman.scoutmediaplayer.ui.viewmodels.ShuffleType

interface PlayerRepository {
    val playerState: StateFlow<PlayerState>
    fun play(item: MediaItem)
    fun pause()
    fun toggleShuffleMode(mode: ShuffleType)
    fun getShuffleMode(): ShuffleType
    fun getNowPlayingItem(): MediaItem?
//    fun getPlayerState(): MediatorLiveData<String>
    fun receivePlayerEvents(state: String)
}