package org.sluman.scoutmediaplayer.domain.use_case

import org.sluman.scoutmediaplayer.domain.model.MediaItem
import org.sluman.scoutmediaplayer.domain.repository.MediaItemRepository

class GetAllMediaItemsUseCase(
    private val repository: MediaItemRepository
) {
    operator fun invoke(): List<MediaItem> {
        return repository.getMediaItems()
    }
}