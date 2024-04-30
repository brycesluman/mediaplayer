package org.sluman.scoutmediaplayer.feature_presentation.ui

sealed class MediaItemsEvent {
    data object MediaItems: MediaItemsEvent()
}