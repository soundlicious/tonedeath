package dev.pabloexposito.menu

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import dev.pabloexposito.navigation.MenuGraph

fun NavGraphBuilder.menuGraph() {
    navigation(startDestination = MenuScreen.path, route = MenuGraph.path) {
        menuDestination()
    }
}