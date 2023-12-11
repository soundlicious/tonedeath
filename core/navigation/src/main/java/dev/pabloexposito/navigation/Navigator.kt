package dev.pabloexposito.navigation

import androidx.lifecycle.LiveData
import androidx.navigation.NavOptions
import androidx.navigation.navOptions

data class NavigationRules(
    val singleTop: Boolean = false,
    val restoreState: Boolean = false,
    val popUpToInclusive: Boolean = false,
    val popUpToSaveState: Boolean = false,
) {
    companion object {
        val topLevelNavigationRules = NavigationRules(
            singleTop = true,
            restoreState = false,
            popUpToInclusive = false,
            popUpToSaveState = false
        )
    }
}

fun NavigationRules.toNavOptions(popToId: Int? = null): NavOptions {
    return navOptions {
        popToId?.let {
            popUpTo(it) {
                saveState = this@toNavOptions.popUpToSaveState
                inclusive = this@toNavOptions.popUpToInclusive
            }
        }
        launchSingleTop = this@toNavOptions.singleTop
        restoreState = this@toNavOptions.restoreState
    }
}

interface Navigator {

    val showBackButton: LiveData<Boolean>

    fun navigateTo(screen: BaseScreen, params: ScreenParams? = null, rules: NavigationRules? = null)

    fun navigateUp()
}