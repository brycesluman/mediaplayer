package org.sluman.scoutmediaplayer.feature_presentation.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.sluman.scoutmediaplayer.feature_player.Player
import org.sluman.scoutmediaplayer.feature_player.PlayerImpl
import org.sluman.scoutmediaplayer.feature_presentation.data.repository.MediaItemRepositoryImpl
import org.sluman.scoutmediaplayer.feature_presentation.data.repository.PlayerRepositoryImpl
import org.sluman.scoutmediaplayer.feature_presentation.data.repository.RecentsRepositoryImpl
import org.sluman.scoutmediaplayer.feature_presentation.domain.repository.MediaItemRepository
import org.sluman.scoutmediaplayer.feature_presentation.domain.repository.PlayerRepository
import org.sluman.scoutmediaplayer.feature_presentation.domain.repository.RecentsRepository
import org.sluman.scoutmediaplayer.feature_presentation.domain.use_case.AddItemToRecentsUseCase
import org.sluman.scoutmediaplayer.feature_presentation.domain.use_case.GetAllMediaItemsUseCase
import org.sluman.scoutmediaplayer.feature_presentation.domain.use_case.GetNowPlayingItemUseCase
import org.sluman.scoutmediaplayer.feature_presentation.domain.use_case.GetShuffleModeUseCase
import org.sluman.scoutmediaplayer.feature_presentation.domain.use_case.MediaItemUseCases
import org.sluman.scoutmediaplayer.feature_presentation.domain.use_case.PauseUseCase
import org.sluman.scoutmediaplayer.feature_presentation.domain.use_case.PlayMostRecentItemUseCase
import org.sluman.scoutmediaplayer.feature_presentation.domain.use_case.PlayRandomUseCase
import org.sluman.scoutmediaplayer.feature_presentation.domain.use_case.PlayRepeatAllUseCase
import org.sluman.scoutmediaplayer.feature_presentation.domain.use_case.PlayRepeatOneUseCase
import org.sluman.scoutmediaplayer.feature_presentation.domain.use_case.PlayUseCase
import org.sluman.scoutmediaplayer.feature_presentation.domain.use_case.PlayerUseCases
import org.sluman.scoutmediaplayer.feature_presentation.domain.use_case.RecentsUseCases
import org.sluman.scoutmediaplayer.feature_presentation.domain.use_case.ToggleShuffleModeUseCase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePlayerUseCases(
        playerRepository: PlayerRepository,
        mediaItemRepository: MediaItemRepository
    ): PlayerUseCases {
        return PlayerUseCases(
            playMediaItemUseCase = PlayUseCase(playerRepository),
            pauseUseCase = PauseUseCase(playerRepository),
            playRandomUseCase = PlayRandomUseCase(playerRepository, mediaItemRepository),
            playRepeatAllUseCase = PlayRepeatAllUseCase(playerRepository, mediaItemRepository),
            playRepeatOneUseCase = PlayRepeatOneUseCase(playerRepository),
            toggleShuffleModeUseCase = ToggleShuffleModeUseCase(playerRepository),
            getNowPlayingItemUseCase = GetNowPlayingItemUseCase(playerRepository),
            getShuffleModeUseCase = GetShuffleModeUseCase(playerRepository)
        )
    }

    @Provides
    @Singleton
    fun provideRecentsUseCase(
        playerRepository: PlayerRepository,
        recentsRepository: RecentsRepository
    ): RecentsUseCases {
        return RecentsUseCases(
            addItemToRecentsUseCase = AddItemToRecentsUseCase(recentsRepository),
            playMostRecentItemUseCase = PlayMostRecentItemUseCase(
                recentsRepository,
                playerRepository
            )
        )
    }

    @Provides
    @Singleton
    fun provideRecentRepository(): RecentsRepository {
        return RecentsRepositoryImpl()
    }

    @Provides
    @Singleton
    fun providePlayerRepository(
        player: Player
    ): PlayerRepository {
        return PlayerRepositoryImpl(player)
    }

    @Provides
    @Singleton
    fun provideMediaItemRepository(): MediaItemRepository {
        return MediaItemRepositoryImpl()
    }

    @Provides
    @Singleton
    fun providePlayer(@ApplicationContext appContext: Context): Player {
        return PlayerImpl(appContext)
    }

    @Provides
    @Singleton
    fun provideMediaItemUseCase(repository: MediaItemRepository): MediaItemUseCases {
        return MediaItemUseCases(
            getAllMediaItems = GetAllMediaItemsUseCase(repository)
        )
    }
}