package org.sluman.scoutmediaplayer.feature_presentation.domain.model

data class MediaItem(
    val id: Int,
    val title: String?,
    val artist: String?,
    val trackLength: Int,
    val uri: String?
)
