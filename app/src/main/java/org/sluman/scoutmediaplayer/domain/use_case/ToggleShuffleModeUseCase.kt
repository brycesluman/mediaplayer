package org.sluman.scoutmediaplayer.domain.use_case

import org.sluman.scoutmediaplayer.domain.repository.PlayerRepository
import org.sluman.scoutmediaplayer.ui.viewmodels.ShuffleType

class ToggleShuffleModeUseCase(
    private val repository: PlayerRepository
) {
    operator fun invoke(mode: ShuffleType) {
        repository.toggleShuffleMode(mode)
    }
}