package org.sluman.scoutmediaplayer.feature_presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.sluman.scoutmediaplayer.R
import org.sluman.scoutmediaplayer.feature_presentation.data.UiState
import org.sluman.scoutmediaplayer.feature_presentation.ui.viewmodels.ShuffleType

@Composable
fun PlayerScreen (
    modifier: Modifier = Modifier,
    onPlaylistButtonClicked: () -> Unit = {},
    onPlayButtonClicked: () -> Unit = {},
    onPauseButtonClicked: () -> Unit = {},
    onShuffleButtonClicked: (shuffleType: ShuffleType) -> Unit = {},
    onPlayNextButtonClicked: () -> Unit = {},
    onPlayPreviousButtonClicked: () -> Unit = {},
    state: State<UiState>
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Row(Modifier.fillMaxWidth()) {
            Icon(
                painter = painterResource(R.drawable.whitelogo),
                contentDescription = "Skip Previous Button"
            )
            Spacer(Modifier.weight(1f))
            IconButton(onClick = { onPlaylistButtonClicked() }) {
                Icon(
                    painter = painterResource(R.drawable.queue_music),
                    contentDescription = "Skip Previous Button"
                )
            }
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = state.value.nowPlayingItem?.title ?:"",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.fillMaxWidth().padding(bottom=45.dp),
                    text = state.value.nowPlayingItem?.artist?:"",
                    textAlign = TextAlign.Center
                )
            }
            Row(Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround) {
                Column {
                    IconToggleButton(checked = state.value.shuffleType == ShuffleType.RANDOM,
                        onCheckedChange = { onShuffleButtonClicked(ShuffleType.RANDOM)
                        }) {
                        Icon(
                            painter = painterResource(R.drawable.shuffle),
                            contentDescription = "Radio button icon",
                        )
                    }
                }
                Column {
                    IconToggleButton(checked = state.value.shuffleType == ShuffleType.REPEAT,
                        onCheckedChange = { onShuffleButtonClicked(ShuffleType.REPEAT)
                         }) {
                        Icon(
                            painter = painterResource(R.drawable.repeat),
                            contentDescription = "Radio button icon",
                        )
                    }
                }
                Column {
                    IconToggleButton(checked = state.value.shuffleType == ShuffleType.REPEAT_1,
                        onCheckedChange = { onShuffleButtonClicked(ShuffleType.REPEAT_1)
                        }) {
                        Icon(
                            painter = painterResource(R.drawable.repeat_one),
                            contentDescription = "Radio button icon",
                        )
                    }
                }
            }

            Row(Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround) {
                Column {
                    IconButton(onClick = { onPlayPreviousButtonClicked() }) {
                        Icon(
                            painter = painterResource(R.drawable.skip_previous),
                            contentDescription = "Skip Previous Button"
                        )
                    }

                }
                Column {
                    if (state.value.isPlaying) {
                        IconButton(onClick = {
                            onPauseButtonClicked()
                        }) {
                            Icon(
                                painter = painterResource(R.drawable.pause),
                                contentDescription = "Pause"
                            )
                        }
                    } else {
                        IconButton(onClick = {
                            onPlayButtonClicked()
                        }) {
                            Icon(
                                painter = painterResource(R.drawable.play_arrow),
                                contentDescription = "Play"
                            )
                        }
                    }
                }
                Column {
                    IconButton(onClick = { onPlayNextButtonClicked()}) {
                        Icon(
                            painter = painterResource(R.drawable.skip_next),
                            contentDescription = "Skip Next Button"
                        )
                    }
                }
            }
        }
    }
}