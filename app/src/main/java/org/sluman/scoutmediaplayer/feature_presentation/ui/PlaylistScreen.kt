package org.sluman.scoutmediaplayer.feature_presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.sluman.scoutmediaplayer.R
import org.sluman.scoutmediaplayer.feature_presentation.data.MediaItemState
import org.sluman.scoutmediaplayer.feature_presentation.domain.model.MediaItem

@Composable
fun PlaylistScreen(
    modifier: Modifier = Modifier,
    onPlayerButtonClicked: () -> Unit = {},
    state: State<MediaItemState>,
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Row(Modifier.fillMaxWidth()) {
            Text(
                text = "Library",
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.weight(1f))
            IconButton(onClick = { onPlayerButtonClicked() }) {
                Icon(
                    painter = painterResource(R.drawable.arrow_back),
                    contentDescription = "Back to Player"
                )
            }
        }
        Table(state.value.mediaItems)
    }
}
@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float,
    fontWeight: FontWeight
) {
    Text(
        text = text,
        Modifier
            .weight(weight)
            .padding(8.dp),
        fontWeight = fontWeight
    )
}
@Composable
fun Table(mediaItems: List<MediaItem>) {

    val column1Weight = .3f // 30%
    val column2Weight = .4f // 40%
    val column3Weight = .3f // 30%
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(16.dp)) {
        item {
            Row(Modifier) {
                TableCell(text = "Song Title",
                    weight = column1Weight,
                    fontWeight = FontWeight.Bold)
                TableCell(text = "Artist",
                    weight = column2Weight,
                    fontWeight = FontWeight.Bold)
                TableCell(text = "Track Length",
                    weight = column3Weight,
                    fontWeight = FontWeight.Bold)
            }
        }
        items(mediaItems) {
            Row(Modifier.fillMaxWidth()) {
                TableCell(text = it.title.toString(),
                    weight = column1Weight,
                    fontWeight = FontWeight.Normal)
                TableCell(text = it.artist.toString(),
                    weight = column2Weight,
                    fontWeight = FontWeight.Normal)
                TableCell(text = stringForTime(it.trackLength),
                    weight = column3Weight,
                    fontWeight = FontWeight.Normal)
            }
        }
    }
}
private fun stringForTime(timeMs: Int): String {
    val totalSeconds = timeMs / 1000
    val seconds = totalSeconds % 60
    val minutes = totalSeconds / 60 % 60
    val hours = totalSeconds / 3600
    return if (hours > 0) {
        "%d:%02d:%02d".format(hours, minutes, seconds)
    } else {
        "%02d:%02d".format(minutes, seconds)
    }
}