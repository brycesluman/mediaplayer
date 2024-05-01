package org.sluman.scoutmediaplayer.feature_presentation.domain.use_case

import org.sluman.scoutmediaplayer.feature_presentation.domain.model.MediaItem
import org.sluman.scoutmediaplayer.feature_presentation.domain.repository.PlayerRepository

class PlayUseCase(
    private val repository: PlayerRepository
) {
    operator fun invoke(mediaItem: MediaItem) {

        repository.play(mediaItem, true)
    }
}