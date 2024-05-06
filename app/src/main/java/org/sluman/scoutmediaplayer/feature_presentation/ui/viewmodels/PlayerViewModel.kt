package org.sluman.scoutmediaplayer.feature_presentation.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.sluman.scoutmediaplayer.feature_presentation.data.UiState
import org.sluman.scoutmediaplayer.feature_presentation.domain.model.MediaItem
import org.sluman.scoutmediaplayer.feature_presentation.domain.repository.PlayerRepository
import org.sluman.scoutmediaplayer.feature_presentation.domain.use_case.PlayerUseCases
import org.sluman.scoutmediaplayer.feature_presentation.domain.use_case.MediaItemCacheUseCases
import org.sluman.scoutmediaplayer.feature_presentation.ui.PlayerEvent
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val playerUseCases: PlayerUseCases,
    @Named("RecentsUseCases") private val recentsUseCases: MediaItemCacheUseCases,
    @Named("UpcomingUseCases") private val upcomingUseCases: MediaItemCacheUseCases,
    private val playerRepository: PlayerRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
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

            is PlayerEvent.Pause ->
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
        playerUseCases.toggleShuffleModeUseCase(shuffleType)
        _uiState.value = uiState.value.copy(
            shuffleType = shuffleType
        )
    }

    private fun skipPrevious() {
        //find previous from recents
        // add currently playing to upcoming
        playerUseCases.getNowPlayingItemUseCase()
            ?.let {
                upcomingUseCases.addItemToCacheUseCase(it)
            }
        recentsUseCases.playTopItemUseCase()
    }

    private fun skipNext() {
        //if upcoming cache is populated play that now.

        playerUseCases.getNowPlayingItemUseCase()
            ?.let { recentsUseCases.addItemToCacheUseCase(it) }
        Log.d("PlayerViewModel", "skipNext" + playerRepository.getShuffleMode())
        if (upcomingUseCases.shouldPlayFromCache()) {
            upcomingUseCases.playTopItemUseCase()
        } else {
            when (playerRepository.getShuffleMode()) {
                ShuffleType.RANDOM -> playerUseCases.playRandomUseCase()
                ShuffleType.REPEAT -> playerUseCases.playRepeatAllUseCase()
                ShuffleType.REPEAT_1 -> playerUseCases.playRepeatOneUseCase()
            }
        }
    }

    private fun pause() {
        _uiState.value = uiState.value.copy(
            isPlaying = false
        )
        playerUseCases.pauseUseCase()
    }

    private fun playMediaItem(item: MediaItem) {
        _uiState.value = uiState.value.copy(
            isPlaying = true,
            nowPlayingItem = item
        )
        playerUseCases.playMediaItemUseCase(item)
        // set now playing id
        // trigger play in the player
    }

}

enum class ShuffleType {
    RANDOM,
    REPEAT,
    REPEAT_1
}