package org.sluman.scoutmediaplayer.domain.repository

import org.sluman.scoutmediaplayer.domain.model.MediaItem


interface MediaItemRepository {
    fun getMediaItems(): List<MediaItem>
}