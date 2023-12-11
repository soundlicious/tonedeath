package dev.pabloexposito.practice

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import dev.pabloexposito.navigation.PracticeGraph

fun NavGraphBuilder.practiceGraph() {
    navigation(startDestination = PracticeScreen.path, route = PracticeGraph.path) {
        gameOverDestination()
        practiceDestination()
    }
}