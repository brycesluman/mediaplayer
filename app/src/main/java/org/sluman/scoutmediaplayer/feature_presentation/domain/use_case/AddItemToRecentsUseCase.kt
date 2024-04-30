package org.sluman.scoutmediaplayer.feature_presentation.domain.use_case

import org.sluman.scoutmediaplayer.feature_presentation.domain.model.MediaItem
import org.sluman.scoutmediaplayer.feature_presentation.domain.repository.RecentsRepository


class AddItemToRecentsUseCase(
    private val repository: RecentsRepository
) {
    operator fun invoke(mediaItem: MediaItem) {
        repository.addToRecents(mediaItem)
    }
}