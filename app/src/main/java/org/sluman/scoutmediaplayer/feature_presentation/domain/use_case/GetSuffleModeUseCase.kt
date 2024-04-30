package org.sluman.scoutmediaplayer.feature_presentation.domain.use_case

import org.sluman.scoutmediaplayer.feature_presentation.domain.repository.PlayerRepository
import org.sluman.scoutmediaplayer.feature_presentation.ui.viewmodels.ShuffleType

class GetShuffleModeUseCase(
    private val repository: PlayerRepository
) {
    operator fun invoke(): ShuffleType {
        return repository.getShuffleMode()
    }
}