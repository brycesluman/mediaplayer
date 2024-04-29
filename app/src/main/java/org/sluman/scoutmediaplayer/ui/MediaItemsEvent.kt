package org.sluman.scoutmediaplayer.ui

sealed class MediaItemsEvent {
    data object MediaItems: MediaItemsEvent()
}