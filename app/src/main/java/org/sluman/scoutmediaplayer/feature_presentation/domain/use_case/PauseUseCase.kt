package org.sluman.scoutmediaplayer.feature_presentation.domain.use_case

import org.sluman.scoutmediaplayer.feature_presentation.domain.repository.PlayerRepository

class PauseUseCase(
    private val repository: PlayerRepository
) {
    operator fun invoke() {
        repository.pause()
    }
}