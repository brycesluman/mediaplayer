package org.sluman.scoutmediaplayer.feature_presentation.domain.use_case

import android.util.Log
import org.sluman.scoutmediaplayer.feature_presentation.domain.repository.MediaItemRepository
import org.sluman.scoutmediaplayer.feature_presentation.domain.repository.PlayerRepository

class PlayRandomUseCase (
    private val playerRepository: PlayerRepository,
    private val mediaItemRepository: MediaItemRepository
    ) {
        operator fun invoke() {
            val randomItem = mediaItemRepository.getMediaItems().filter {
                playerRepository.getNowPlayingItem() != it
            }.random()
            Log.d("PlayerViewModel", "randomItem: $randomItem")

            playerRepository.play(randomItem)
        }
}