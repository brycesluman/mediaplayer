package org.sluman.scoutmediaplayer.feature_presentation.data.repository

import org.sluman.scoutmediaplayer.feature_presentation.domain.model.MediaItem
import org.sluman.scoutmediaplayer.feature_presentation.domain.repository.MediaCacheRepository

class RecentsRepositoryImpl : MediaCacheRepository {
    private var recents = ArrayDeque<MediaItem>()

    override fun addToCache(item: MediaItem) {
        if (!recents.contains(item)) {
            recents.addFirst(item)
        }
    }

    override fun getTopCacheItem(): MediaItem {
        return recents.removeFirst()
    }

    override fun getCacheSize(): Int {
        return recents.size
    }

    override fun peekAtTopItem(): MediaItem {
        return recents.first()
    }

}