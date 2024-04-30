package org.sluman.scoutmediaplayer.feature_presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import org.sluman.scoutmediaplayer.feature_presentation.ui.ScoutApp
import org.sluman.scoutmediaplayer.feature_presentation.ui.theme.ScoutTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScoutTheme {
                ScoutApp()
            }
        }
    }
}