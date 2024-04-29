package org.sluman.scoutmediaplayer.ui

import org.sluman.scoutmediaplayer.domain.model.MediaItem
import org.sluman.scoutmediaplayer.ui.viewmodels.ShuffleType

sealed class PlayerEvent {
    data class PlayMediaItem(val mediaItem: MediaItem): PlayerEvent()
    data object Pause: PlayerEvent()
    data object SkipPrevious: PlayerEvent()
    data object SkipNext: PlayerEvent()
    data class ToggleShuffleType(val shuffleType: ShuffleType) : PlayerEvent()
}