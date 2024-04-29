package org.sluman.scoutmediaplayer.domain.use_case

import org.sluman.scoutmediaplayer.domain.model.MediaItem
import org.sluman.scoutmediaplayer.domain.repository.PlayerRepository

class GetNowPlayingItemUseCase(
    private val repository: PlayerRepository
) {
    operator fun invoke(): MediaItem? {
        return repository.getNowPlayingItem()
    }
}