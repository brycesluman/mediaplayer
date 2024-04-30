package org.sluman.scoutmediaplayer.feature_presentation.data

import org.sluman.scoutmediaplayer.feature_presentation.domain.model.MediaItem
import org.sluman.scoutmediaplayer.feature_presentation.ui.viewmodels.ShuffleType

data class UiState(
    val isPlaying: Boolean = false,
    val isEnded: Boolean = false,
    val isError: Boolean = false,
    val nowPlayingItem: MediaItem? = null,
    val shuffleType: ShuffleType = ShuffleType.REPEAT
)