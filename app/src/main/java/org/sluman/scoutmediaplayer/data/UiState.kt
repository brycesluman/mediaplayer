package org.sluman.scoutmediaplayer.data

import org.sluman.scoutmediaplayer.domain.model.MediaItem
import org.sluman.scoutmediaplayer.ui.viewmodels.ShuffleType

data class UiState (
    val isPlaying: Boolean = false,
    val isEnded: Boolean = false,
    val isError: Boolean = false,
    val nowPlayingItem: MediaItem? = null,
    val shuffleType: ShuffleType = ShuffleType.REPEAT
)