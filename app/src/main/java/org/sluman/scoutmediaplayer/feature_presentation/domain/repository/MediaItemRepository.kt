package org.sluman.scoutmediaplayer.feature_presentation.domain.repository

import org.sluman.scoutmediaplayer.feature_presentation.domain.model.MediaItem


interface MediaItemRepository {
    fun getMediaItems(): List<MediaItem>
}