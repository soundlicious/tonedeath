package dev.pabloexposito.menu

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.pabloexposito.navigation.LearningGraph
import dev.pabloexposito.navigation.Navigator
import dev.pabloexposito.navigation.PracticeGraph
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    navigator: Navigator
) : Navigator by navigator, ViewModel() {

    fun onLearningClick() {
        navigateTo(LearningGraph)
    }

    fun onPracticeClick() {
        navigateTo(PracticeGraph)
    }
}