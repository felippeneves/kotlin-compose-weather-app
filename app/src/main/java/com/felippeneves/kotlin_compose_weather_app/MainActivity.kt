package com.felippeneves.kotlin_compose_weather_app

import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowInsetsControllerCompat
import com.felippeneves.kotlin_compose_weather_app.presentation.navigation.AppNavigation
import com.felippeneves.kotlin_compose_weather_app.ui.theme.KotlinComposeWeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setWhiteIconsOnStatusBar(window)
        setContent {
            KotlinComposeWeatherAppTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    Surface(modifier = Modifier.fillMaxSize()) {
        AppNavigation()
    }
}

private fun setWhiteIconsOnStatusBar(window: Window) {
    WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false
}