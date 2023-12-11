package dev.pabloexposito.learning

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.pabloexposito.common.InitialParameter
import dev.pabloexposito.data.repository.PinyinRepository
import dev.pabloexposito.navigation.Navigator
import javax.inject.Inject

@HiltViewModel
internal class DetailViewModel @Inject constructor(
    navigator: Navigator,
    pinyinRepository: PinyinRepository,
    @InitialParameter private val init: String,
) : Navigator by navigator, ViewModel() {

    val details = pinyinRepository.getFinalsByInitials(initial = init)

    fun onFinalSelected(final: String) {
        navigateTo(
            screen = ListeningScreen,
            params = ListeningScreenParameters(init, final),
            rules = null
        )
    }
}