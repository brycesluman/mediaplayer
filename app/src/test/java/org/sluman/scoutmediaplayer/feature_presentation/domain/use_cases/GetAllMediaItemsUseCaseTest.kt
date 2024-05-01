package org.sluman.scoutmediaplayer.feature_presentation.domain.use_cases

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.sluman.scoutmediaplayer.feature_presentation.domain.repository.MediaItemRepository
import org.sluman.scoutmediaplayer.feature_presentation.domain.repository.RecentsRepository

@ExperimentalCoroutinesApi
class GetAllMediaItemsUseCaseTest {
    @Mock
    lateinit var repository: MediaItemRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun getAllMediaItemsUseCaseTest() {

    }
}