package dev.pabloexposito.practice

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import dev.pabloexposito.navigation.PracticeGraph

fun NavGraphBuilder.practiceGraph(windowSizeClass: WindowSizeClass) {
    navigation(startDestination = PracticeScreen.path, route = PracticeGraph.path) {
        gameOverDestination()
        practiceDestination(windowSizeClass.widthSizeClass)
    }
}