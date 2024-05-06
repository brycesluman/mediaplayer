package org.sluman.scoutmediaplayer.feature_presentation.domain.repository

import org.sluman.scoutmediaplayer.feature_presentation.domain.model.MediaItem

interface MediaCacheRepository {
    fun addToCache(item: MediaItem)

    fun getTopCacheItem(): MediaItem

    fun getCacheSize(): Int

    fun peekAtTopItem(): MediaItem
}