package org.sluman.scoutmediaplayer.feature_presentation.domain.use_case

import org.sluman.scoutmediaplayer.feature_presentation.domain.repository.PlayerRepository

class PlayRepeatOneUseCase (
    private val playerRepository: PlayerRepository
) {
    operator fun invoke() {
        playerRepository.getNowPlayingItem()?.let { playerRepository.play(it, false) }
    }
}