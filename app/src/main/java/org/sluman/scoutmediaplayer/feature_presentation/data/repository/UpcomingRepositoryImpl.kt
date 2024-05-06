package org.sluman.scoutmediaplayer.feature_presentation.data.repository

import org.sluman.scoutmediaplayer.feature_presentation.domain.model.MediaItem
import org.sluman.scoutmediaplayer.feature_presentation.domain.repository.MediaCacheRepository

class UpcomingRepositoryImpl: MediaCacheRepository {
    private var upcoming = ArrayDeque<MediaItem>()

    override fun addToCache(item: MediaItem) {
        if (!upcoming.contains(item)) {
            upcoming.addFirst(item)
        }
    }

    override fun getTopCacheItem(): MediaItem {
        return upcoming.removeFirst()
    }

    override fun getCacheSize(): Int {
        return upcoming.size
    }

    override fun peekAtTopItem(): MediaItem {
        return upcoming.first()
    }
}