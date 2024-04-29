package org.sluman.scoutmediaplayer.domain.repository

import org.sluman.scoutmediaplayer.domain.model.MediaItem

interface RecentsRepository {
    fun addToRecents(item: MediaItem)

    fun getMostRecentItem(): MediaItem

    fun getRecentSize(): Int

    fun peekAtTopItem(): MediaItem
}