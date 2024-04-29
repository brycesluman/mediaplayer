package org.sluman.scoutmediaplayer.data

import org.sluman.scoutmediaplayer.domain.model.MediaItem

data class MediaItemState(
    val mediaItems: List<MediaItem> = emptyList(),
    val isLoading: Boolean = false,
)
