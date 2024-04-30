package org.sluman.scoutmediaplayer.feature_presentation.domain.repository

import org.sluman.scoutmediaplayer.feature_presentation.domain.model.MediaItem

interface RecentsRepository {
    fun addToRecents(item: MediaItem)

    fun getMostRecentItem(): MediaItem

    fun getRecentSize(): Int

    fun peekAtTopItem(): MediaItem
}