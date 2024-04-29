package org.sluman.scoutmediaplayer.domain.use_case

import org.sluman.scoutmediaplayer.domain.model.MediaItem
import org.sluman.scoutmediaplayer.domain.repository.PlayerRepository

class PlayUseCase(
    private val repository: PlayerRepository
) {
    operator fun invoke(mediaItem: MediaItem) {

        repository.play(mediaItem)
    }
}