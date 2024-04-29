package org.sluman.scoutmediaplayer.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.sluman.scoutmediaplayer.data.UiState
import org.sluman.scoutmediaplayer.domain.model.MediaItem
import org.sluman.scoutmediaplayer.domain.repository.PlayerRepository
import org.sluman.scoutmediaplayer.domain.use_case.PlayerUseCases
import org.sluman.scoutmediaplayer.domain.use_case.RecentsUseCases
import org.sluman.scoutmediaplayer.ui.PlayerEvent
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val playerUseCases: PlayerUseCases,
    private val recentsUseCases: RecentsUseCases,
    private val playerRepository: PlayerRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init{
        viewModelScope.launch {
            playerRepository.playerState.collectLatest { state ->
                if (state.isEnded) {
                    skipNext()
                }

                _uiState.value = uiState.value.copy(
                    isPlaying = state.isPlaying,
                    isEnded = state.isEnded,
                    isError = state.isError,
                    nowPlayingItem = state.nowPlayingItem
                )
            }
        }

    }
    fun onEvent(event: PlayerEvent) {
        when (event) {
            is PlayerEvent.PlayMediaItem ->
                playMediaItem(event.mediaItem)
            is  PlayerEvent.Pause ->
                pause()
            is PlayerEvent.SkipNext ->
                skipNext()
            is PlayerEvent.SkipPrevious ->
                skipPrevious()
            is PlayerEvent.ToggleShuffleType ->
                toggleShuffleType(event.shuffleType)
        }
    }

    private fun toggleShuffleType(shuffleType: ShuffleType) {
        playerUseCases.toggleShuffleModeUseCase.invoke(shuffleType)
        _uiState.value = uiState.value.copy(
            shuffleType = shuffleType
        )
    }

    private fun skipPrevious() {
        //find previous from recents
        recentsUseCases.playMostRecentItemUseCase.invoke()
    }

    private fun skipNext() {
        playerUseCases.getNowPlayingItemUseCase.invoke()?.let { recentsUseCases.addItemToRecentsUseCase(it) }
        Log.d("PlayerViewModel", "skipNext" + playerRepository.getShuffleMode())
        when(playerRepository.getShuffleMode()) {
            ShuffleType.RANDOM -> playerUseCases.playRandomUseCase.invoke()
            ShuffleType.REPEAT -> playerUseCases.playRepeatAllUseCase.invoke()
            ShuffleType.REPEAT_1 -> playerUseCases.playRepeatOneUseCase.invoke()
        }
    }

    private fun pause() {
        _uiState.value = uiState.value.copy(
            isPlaying = false
        )
        playerUseCases.pauseUseCase.invoke()
    }

    private fun playMediaItem(item: MediaItem) {
        _uiState.value = uiState.value.copy(
            isPlaying = true,
            nowPlayingItem = item
        )
        playerUseCases.playMediaItemUseCase.invoke(item)
        // set now playing id
        // trigger play in the player
    }

}
enum class ShuffleType {
    RANDOM,
    REPEAT,
    REPEAT_1
}