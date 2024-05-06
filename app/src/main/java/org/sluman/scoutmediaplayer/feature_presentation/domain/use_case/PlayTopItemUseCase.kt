package org.sluman.scoutmediaplayer.feature_presentation.domain.use_case

import android.util.Log
import org.sluman.scoutmediaplayer.feature_presentation.domain.repository.PlayerRepository
import org.sluman.scoutmediaplayer.feature_presentation.domain.repository.MediaCacheRepository

class PlayTopItemUseCase (
    private val repository: MediaCacheRepository,
    private val playerRepository: PlayerRepository
) {
    operator fun invoke() {
        if (repository.getCacheSize()>=1) {
            Log.d("PlayerViewModel", "topItem: " + repository.peekAtTopItem())
            playerRepository.play(repository.getTopCacheItem())
        }
    }
}