package org.sluman.scoutmediaplayer.feature_presentation.ui

import org.sluman.scoutmediaplayer.feature_presentation.domain.model.MediaItem
import org.sluman.scoutmediaplayer.feature_presentation.ui.viewmodels.ShuffleType

sealed class PlayerEvent {
    data object Play: PlayerEvent()

    data class PlayMediaItem(val mediaItem: MediaItem): PlayerEvent()
    data object Pause: PlayerEvent()
    data object SkipPrevious: PlayerEvent()
    data object SkipNext: PlayerEvent()
    data class ToggleShuffleType(val shuffleType: ShuffleType) : PlayerEvent()
}