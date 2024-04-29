package org.sluman.scoutmediaplayer.domain.use_case

import android.util.Log
import org.sluman.scoutmediaplayer.domain.repository.PlayerRepository
import org.sluman.scoutmediaplayer.domain.repository.RecentsRepository

class PlayMostRecentItemUseCase (
    private val recentsRepository: RecentsRepository,
    private val playerRepository: PlayerRepository
) {
    operator fun invoke() {
        if (recentsRepository.getRecentSize()>=1) {
            Log.d("PlayerViewModel", "topItem: " + recentsRepository.peekAtTopItem())
            playerRepository.play(recentsRepository.getMostRecentItem())
        }
    }
}