package dev.pabloexposito.tonedeath

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import dev.pabloexposito.designsystem.theme.AppTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import dev.pabloexposito.navigation.Navigator
import dev.pabloexposito.tonedeath.ui.screens.MainRoute
import javax.inject.Inject

@AndroidEntryPoint
internal class MainActivity : ComponentActivity() {

    @Inject
    lateinit var appNavigator: Navigator

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            AppTheme {
                MainRoute(
                    modifier = Modifier.fillMaxSize(),
                    navigator = appNavigator as AppNavigator,
                    windowSizeClass = windowSizeClass
                )
            }
        }
    }
}