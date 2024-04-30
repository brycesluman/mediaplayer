package org.sluman.scoutmediaplayer.feature_presentation.data

import org.sluman.scoutmediaplayer.feature_presentation.domain.model.MediaItem

data class MediaItemState(
    val mediaItems: List<MediaItem> = emptyList(),
    val isLoading: Boolean = false,
)
