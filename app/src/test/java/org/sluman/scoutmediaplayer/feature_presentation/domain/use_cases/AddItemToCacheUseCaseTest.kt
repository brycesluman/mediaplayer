package org.sluman.scoutmediaplayer.feature_presentation.domain.use_cases

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import org.sluman.scoutmediaplayer.feature_presentation.domain.model.MediaItem
import org.sluman.scoutmediaplayer.feature_presentation.domain.repository.MediaCacheRepository
import org.sluman.scoutmediaplayer.feature_presentation.domain.use_case.AddItemToCacheUseCase

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalCoroutinesApi
class AddItemToCacheUseCaseTest {
    @Mock
    lateinit var recentsRepository: MediaCacheRepository
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }
    @Test
    fun addItemToRecentsUseCaseTest() {
            AddItemToCacheUseCase(recentsRepository).invoke(testItem)

            verify(recentsRepository).addToCache(testItem)

    }
    private val testItem = MediaItem(
        id = 0,
        title = "Claire de Lune",
        artist = "Claude Debussy",
        uri = "Clair_de_Lune.mp3",
        trackLength = 312003
    )
}
