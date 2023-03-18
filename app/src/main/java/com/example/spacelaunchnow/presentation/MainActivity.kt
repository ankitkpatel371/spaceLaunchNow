package com.example.spacelaunchnow.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.spacelaunchnow.presentation.navigation.Navigation
import com.example.spacelaunchnow.presentation.ui.theme.SpacelaunchnowTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpacelaunchnowTheme {
                Navigation()
            }
        }
    }
}
