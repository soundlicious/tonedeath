package dev.pabloexposito.learning

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.pabloexposito.data.repository.PinyinRepository
import dev.pabloexposito.navigation.Navigator
import javax.inject.Inject


@HiltViewModel
class LearningViewModel @Inject constructor(
    navigator: Navigator,
    private val pinyinRepository: PinyinRepository
) : Navigator by navigator, ViewModel() {

    fun getFinals(initial: String) = pinyinRepository.getFinalsByInitials(initial)
    fun getDistinctInitials() = pinyinRepository.getInitials()

    fun onInitialClick(init: String) {
        navigateToDetails(init)
    }

    fun onFinalClick(init: String, final: String) {
        navigateToListening(init, final)
    }

    private fun navigateToDetails(init: String) {
        navigateTo(
            screen = LearningDetailsScreen,
            params = LearningDetailsScreenParameters(init = init)
        )
    }

    private fun navigateToListening(init: String, final: String) {
        navigateTo(
            screen = ListeningScreen,
            params = ListeningScreenParameters(init, final)
        )
    }
}