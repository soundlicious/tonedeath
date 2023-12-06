package dev.pabloexposito.learning

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.pabloexposito.common.FinalParameter
import dev.pabloexposito.common.InitialParameter
import dev.pabloexposito.model.data.Tone
import dev.pabloexposito.model.data.Pinyin
import dev.pabloexposito.common.PlayerState
import dev.pabloexposito.common.SoundPlayer
import dev.pabloexposito.model.data.PinYinSoundInfo
import dev.pabloexposito.model.data.getRandomSoundInfoWithSpecificTone
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ListeningViewModel @Inject constructor(
    @InitialParameter initialParameter: String,
    @FinalParameter finalParameter: String,
    private val soundPlayer: SoundPlayer<PinYinSoundInfo>,
) : ViewModel() {
    private val _enableButtons: MutableLiveData<Boolean> = MutableLiveData(true)
    val enableButtons: LiveData<Boolean> get() = _enableButtons
    val selectedPinYin = Pinyin(initialParameter, finalParameter)
    private val pinYinSoundInfos by lazy {
        val tone1 = selectedPinYin.getRandomSoundInfoWithSpecificTone(tone = Tone.TONE1)
        mapOf(
            Tone.TONE1 to tone1,
            Tone.TONE2 to tone1.withNewTone(Tone.TONE2),
            Tone.TONE3 to tone1.withNewTone(Tone.TONE3),
            Tone.TONE4 to tone1.withNewTone(Tone.TONE4),
        )
    }

    init {
        viewModelScope.launch {
            soundPlayer.playerState.asFlow().collect { playerState ->
                when (playerState) {
                    is PlayerState.Completed -> _enableButtons.value = true
                    is PlayerState.Error -> {}
                    is PlayerState.ReadyToPlay -> {
                        _enableButtons.value = false
                        playerState.execute()
                    }
                }
            }
        }
    }

    fun onToneSelected(tone: Tone) {
        pinYinSoundInfos[tone]?.let { pinYinSoundInfo ->
            soundPlayer.prepareSound(pinYinSoundInfo)
        }
    }
}