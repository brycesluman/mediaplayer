package org.sluman.scoutmediaplayer.feature_presentation.domain.use_cases

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import org.sluman.scoutmediaplayer.feature_presentation.domain.model.MediaItem
import org.sluman.scoutmediaplayer.feature_presentation.domain.repository.MediaItemRepository
import org.sluman.scoutmediaplayer.feature_presentation.domain.repository.PlayerRepository
import org.sluman.scoutmediaplayer.feature_presentation.domain.use_case.GetNowPlayingItemUseCase

class GetNowPlayingItemUseCaseTest {
    @Mock
    lateinit var repository: PlayerRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun getNowPlayingItemUseCaseTest() {
        GetNowPlayingItemUseCase(repository).invoke()

        verify(repository).getNowPlayingItem()
    }

    private val testItem = MediaItem(
        id = 0,
        title = "Claire de Lune",
        artist = "Claude Debussy",
        uri = "Clair_de_Lune.mp3",
        trackLength = 312003
    )
}