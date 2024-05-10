package org.sluman.scoutmediaplayer.feature_presentation.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.sluman.scoutmediaplayer.R
import org.sluman.scoutmediaplayer.feature_presentation.ui.viewmodels.MainViewModel
import org.sluman.scoutmediaplayer.feature_presentation.ui.viewmodels.PlayerViewModel

/**
 * enum values that represent the screens in the app
 */
enum class MainScreen(@StringRes val title: Int) {
    Player(title = R.string.app_name),
    Playlist(title = R.string.playlist),
}

@Composable
fun ScoutApp(
    navController: NavHostController = rememberNavController()
) {


    Scaffold(

    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MainScreen.Player.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(route = MainScreen.Player.name) {
                val playerViewModel: PlayerViewModel = hiltViewModel()
                PlayerScreen(
                    onPlaylistButtonClicked = {
                        navController.navigate(MainScreen.Playlist.name)
                    },
                    onPlayButtonClicked = {
                        playerViewModel.onEvent(PlayerEvent.Play)
                    },
                    onPauseButtonClicked = {
                        playerViewModel.onEvent(PlayerEvent.Pause)
                    },
                    onShuffleButtonClicked = {
                        playerViewModel.onEvent(PlayerEvent.ToggleShuffleType(it))
                    },
                    onPlayNextButtonClicked = {
                        playerViewModel.onEvent(PlayerEvent.SkipNext)
                    },
                    onPlayPreviousButtonClicked = {
                        playerViewModel.onEvent(PlayerEvent.SkipPrevious)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium)),
                    state = playerViewModel.uiState.collectAsState()
                )
            }
            composable(route = MainScreen.Playlist.name) {
                val viewModel: MainViewModel = hiltViewModel()
                PlaylistScreen(
                    onPlayerButtonClicked = { navController.navigateUp() },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium)),
                    state = viewModel.uiState.collectAsState()
                )
            }
        }
    }
}
