package org.sluman.scoutmediaplayer.feature_presentation.domain.use_case

data class PlayerUseCases (
    val playMediaItemUseCase: PlayUseCase,
    val pauseUseCase: PauseUseCase,
    val playRepeatAllUseCase: PlayRepeatAllUseCase,
    val playRandomUseCase: PlayRandomUseCase,
    val playRepeatOneUseCase: PlayRepeatOneUseCase,
    val toggleShuffleModeUseCase: ToggleShuffleModeUseCase,
    val getShuffleModeUseCase: GetShuffleModeUseCase,
    val getNowPlayingItemUseCase: GetNowPlayingItemUseCase
)