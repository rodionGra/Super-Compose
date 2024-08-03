package com.supercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.Scaffold
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.supercompose.navigation.NavigationComponent
import com.supercompose.ui.theme.SuperComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperComposeTheme {
                val navController = rememberAnimatedNavController()
                Scaffold { paddings ->
                    paddings //TODO
                    NavigationComponent(navController)
                }
            }
        }
    }
}
