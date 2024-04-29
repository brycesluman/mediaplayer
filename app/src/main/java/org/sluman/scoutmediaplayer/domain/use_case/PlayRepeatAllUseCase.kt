package org.sluman.scoutmediaplayer.domain.use_case

import android.util.Log
import org.sluman.scoutmediaplayer.domain.repository.MediaItemRepository
import org.sluman.scoutmediaplayer.domain.repository.PlayerRepository

class PlayRepeatAllUseCase (
    private val playerRepository: PlayerRepository,
    private val mediaItemRepository: MediaItemRepository
) {
    operator fun invoke() {
        val curIndex = mediaItemRepository.getMediaItems().indexOf(playerRepository.getNowPlayingItem())
        if (mediaItemRepository.getMediaItems().size-1 == curIndex) {
            //last item, go back to zero
            playerRepository.play(mediaItemRepository.getMediaItems()[0])
            return
        }
        val nextItem = mediaItemRepository.getMediaItems()[curIndex+1]
        Log.d("PlayerViewModel", "nextItem: $nextItem")

        playerRepository.play(nextItem)
    }
}