package org.sluman.scoutmediaplayer.data.repository

import org.sluman.scoutmediaplayer.domain.model.MediaItem
import org.sluman.scoutmediaplayer.domain.repository.MediaItemRepository

class MediaItemRepositoryImpl : MediaItemRepository {
    override fun getMediaItems(): List<MediaItem> {
        return listOf(
            MediaItem(
                id = 0,
                title = "Claire de Lune",
                artist = "Claude Debussy",
                uri = "Clair_de_Lune.mp3",
                trackLength = 312003
            ),
            MediaItem(
                id = 2,
                title = "Jazz de Luxe",
                artist = "Sylvain Lux",
                uri = "Jazz_de_Luxe.mp3",
                trackLength = 612055
            ),
            MediaItem(
                id = 3,
                title = "La Paloma",
                artist = "Julio Iglesias",
                uri = "La_Paloma.mp3",
                trackLength = 360050
            ),
            MediaItem(
                id = 4,
                title = "M Appari Martha",
                artist = "Luciano Pavarotti",
                uri = "M_Appari_Martha.mp3",
                trackLength = 180600
            ),
            MediaItem(
                id = 5,
                title = "Mary had a Little Lamb",
                artist = "Sarah Josepha Hale",
                uri = "Mary_Had_a_Little_Lamb.mp3",
                trackLength = 185000
            ),
            MediaItem(
                id = 6,
                title = "Minuet in G Flat Major",
                artist = "Christian Petzold",
                uri = "Minuet_in_G_Flat_major.mp3",
                trackLength = 120320
            ),
            MediaItem(
                id = 7,
                title = "Moonlight",
                artist = "Kali Uchis",
                uri = "Moonlight.mp3",
                trackLength = 312000
            ),
            MediaItem(
                id = 8,
                title = "Moonlight Bay",
                artist = "Doris Day",
                uri = "Moonlight_Bay.mp3",
                trackLength = 180000
            ),
            MediaItem(
                id = 9,
                title = "Narowode Melodye",
                artist = "Aleksander Iwanowski",
                uri = "Narowode_Melodye.mp3",
                trackLength = 312000
            )
        )
    }
}