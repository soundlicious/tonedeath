package dev.pabloexposito.tonedeath

import androidx.collection.forEach
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import dev.pabloexposito.common.SCREEN_NAME_PARAMETER
import dev.pabloexposito.navigation.BaseScreen
import dev.pabloexposito.navigation.MenuGraph
import dev.pabloexposito.navigation.NavigationRules
import dev.pabloexposito.navigation.ScreenParams
import dev.pabloexposito.navigation.toNavOptions
import dev.pabloexposito.practice.GameOverScreen
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppNavigator @Inject constructor(
    private val screenTitleResolver: ScreenTitleResolver
) : dev.pabloexposito.navigation.Navigator {
    private val _showBackButton = MutableLiveData(false)
    override val showBackButton: LiveData<Boolean> get() = _showBackButton
    private val _appBarTitle = MutableLiveData<String?>(null)
    val appBarTitle: LiveData<String?> get() = _appBarTitle
    var navController: NavController? = null
        set(value) {
            println("AppNavigator : setting navController: $value")
            field = value
        }

    init {
        println("AppNavigator : $this init navController $navController")
    }

    override fun navigateTo(
        screen: BaseScreen,
        params: ScreenParams?,
        rules: NavigationRules?
    ) {

        val destination = screen.getRoute(
            screenParams = params,
            title = screenTitleResolver(screen)
        )
        runCatching {
            val navOptions = if (screen is BaseScreen.Graph) {
                (rules ?: NavigationRules.topLevelNavigationRules)
            } else {
                rules
            }?.toNavOptions(navController?.graph?.findStartDestination()?.id)

            navController?.navigate(destination, navOptions = navOptions)
        }.onFailure {
            println("AppNavigator : Error navigating to $screen: $it())")
            navController?.graph?.nodes?.forEach { key, value ->
                println("AppNavigator : $key: $value")
            }
            it.printStackTrace()
        }.onSuccess {
            println("AppNavigator : Success navigating to $screen with destination : $destination")
        }
    }

    override fun navigateUp() {
        val isGameOverScreen =
            navController?.currentDestination?.route?.contains(GameOverScreen.root) ?: false
        if (isGameOverScreen) {
            navigateTo(MenuGraph)
        } else {
            navController?.navigateUp()
        }
    }

    fun onDestinationChanged() {
        val backStackNotEmpty = navController?.previousBackStackEntry != null
        _appBarTitle.value =
            navController?.currentBackStackEntry?.arguments?.getString(SCREEN_NAME_PARAMETER)
        _showBackButton.value = backStackNotEmpty
    }
}