package org.sluman.scoutmediaplayer.domain.use_case

import org.sluman.scoutmediaplayer.domain.repository.PlayerRepository

class PlayRepeatOneUseCase (
    private val playerRepository: PlayerRepository
) {
    operator fun invoke() {
        playerRepository.getNowPlayingItem()?.let { playerRepository.play(it) }
    }
}