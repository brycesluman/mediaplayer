package org.sluman.scoutmediaplayer.feature_presentation.domain.use_case

import org.sluman.scoutmediaplayer.feature_presentation.domain.model.MediaItem
import org.sluman.scoutmediaplayer.feature_presentation.domain.repository.MediaCacheRepository


class AddItemToCacheUseCase(
    private val repository: MediaCacheRepository
) {
    operator fun invoke(mediaItem: MediaItem) {
        repository.addToCache(mediaItem)
    }
}