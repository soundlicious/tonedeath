package dev.pabloexposito.learning

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import dev.pabloexposito.navigation.LearningGraph

fun NavGraphBuilder.learningGraph(windowSizeClass: WindowSizeClass) {
    navigation(startDestination = LearningScreen.path, route = LearningGraph.path) {
        learningDestination(windowSizeClass.widthSizeClass)
        learningDetailsDestination()
        listeningDestination()
    }
}