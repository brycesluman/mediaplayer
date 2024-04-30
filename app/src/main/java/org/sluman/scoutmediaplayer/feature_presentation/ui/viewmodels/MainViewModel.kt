package org.sluman.scoutmediaplayer.feature_presentation.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sluman.scoutmediaplayer.feature_presentation.data.MediaItemState
import org.sluman.scoutmediaplayer.feature_presentation.domain.use_case.MediaItemUseCases
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mediaItemUseCases: MediaItemUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(MediaItemState())
    val uiState: StateFlow<MediaItemState> = _uiState.asStateFlow()

    private var getMediaItemsJob: Job? = null

    init {
        getMediaItems()
    }

    private fun getMediaItems() {
        getMediaItemsJob?.cancel()
        viewModelScope.launch {
            mediaItemUseCases.getAllMediaItems()
                .let { mediaItems ->
                    _uiState.value = uiState.value.copy(
                        mediaItems = mediaItems,
                        isLoading = false
                    )
                }
        }
    }
}