package org.sluman.scoutmediaplayer.data.repository

import org.sluman.scoutmediaplayer.domain.model.MediaItem
import org.sluman.scoutmediaplayer.domain.repository.RecentsRepository

class RecentsRepositoryImpl: RecentsRepository {
    private var recents = ArrayDeque<MediaItem>()

    override fun addToRecents(item: MediaItem) {
        if(!recents.contains(item)) {
            recents.addFirst(item)
        }
    }

    override fun getMostRecentItem(): MediaItem {
        return recents.removeFirst()
    }

    override fun getRecentSize(): Int {
        return recents.size
    }

    override fun peekAtTopItem(): MediaItem {
        return recents.first()
    }

}