package dev.pabloexposito.testing

import androidx.lifecycle.MutableLiveData
import dev.pabloexposito.navigation.BaseScreen
import dev.pabloexposito.navigation.NavigationRules
import dev.pabloexposito.navigation.Navigator
import dev.pabloexposito.navigation.ScreenParams

class FakeNavigator : Navigator {
    val screenStack = mutableListOf<Triple<BaseScreen, ScreenParams?, NavigationRules?>>()
    override val showBackButton: MutableLiveData<Boolean> = MutableLiveData()
    val lastScreen
        get() = screenStack.lastOrNull()

    override fun navigateTo(screen: BaseScreen, params: ScreenParams?, rules: NavigationRules?) {
        screenStack.add(Triple(screen, params, rules))
    }

    override fun navigateUp() {
        screenStack.removeLast()
    }
}