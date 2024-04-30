package org.sluman.scoutmediaplayer.feature_presentation.domain.use_case

import org.sluman.scoutmediaplayer.feature_presentation.domain.model.MediaItem
import org.sluman.scoutmediaplayer.feature_presentation.domain.repository.MediaItemRepository

class GetAllMediaItemsUseCase(
    private val repository: MediaItemRepository
) {
    operator fun invoke(): List<MediaItem> {
        return repository.getMediaItems()
    }
}