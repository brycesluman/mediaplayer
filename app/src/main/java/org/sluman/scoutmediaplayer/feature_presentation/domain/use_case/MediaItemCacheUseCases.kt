package org.sluman.scoutmediaplayer.feature_presentation.domain.use_case

data class MediaItemCacheUseCases (
    val addItemToCacheUseCase: AddItemToCacheUseCase,
    val playTopItemUseCase: PlayTopItemUseCase,
    val shouldPlayFromCache: ShouldPlayFromCacheUseCase
)