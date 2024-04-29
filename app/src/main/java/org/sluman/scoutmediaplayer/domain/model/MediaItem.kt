package org.sluman.scoutmediaplayer.domain.model

data class MediaItem(
    val id: Int,
    val title: String?,
    val artist: String?,
    val trackLength: Int,
    val uri: String?
)
