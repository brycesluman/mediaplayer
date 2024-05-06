package org.sluman.scoutmediaplayer.feature_presentation.domain.use_case

import org.sluman.scoutmediaplayer.feature_presentation.domain.repository.MediaCacheRepository

class ShouldPlayFromCacheUseCase  (
    private val repository: MediaCacheRepository
) {
    operator fun invoke(): Boolean {
        return repository.getCacheSize()>0
    }
}