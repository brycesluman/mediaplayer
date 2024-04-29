package org.sluman.scoutmediaplayer.domain.use_case

import org.sluman.scoutmediaplayer.domain.repository.PlayerRepository

class PauseUseCase(
    private val repository: PlayerRepository
) {
    operator fun invoke() {
        repository.pause()
    }
}