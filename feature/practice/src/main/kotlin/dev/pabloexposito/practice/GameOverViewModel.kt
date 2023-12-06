package dev.pabloexposito.practice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.pabloexposito.model.data.Pinyin
import dev.pabloexposito.model.data.Tone
import dev.pabloexposito.model.data.VoiceNumber
import dev.pabloexposito.common.FinalParameter
import dev.pabloexposito.common.GoodToneParameter
import dev.pabloexposito.common.InitialParameter
import dev.pabloexposito.common.SelectedToneParameter
import dev.pabloexposito.common.VoiceGenderParameter
import dev.pabloexposito.common.VoiceNumberParameter
import dev.pabloexposito.common.PlayerState
import dev.pabloexposito.common.ScoreParameter
import dev.pabloexposito.common.SoundPlayer
import dev.pabloexposito.core.data.VoiceGender
import dev.pabloexposito.model.data.PinYinSoundInfo
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class GameOverViewModel @Inject constructor(
    @InitialParameter initial: String,
    @FinalParameter final: String,
    @SelectedToneParameter selectedTone: String,
    @GoodToneParameter goodTone: String,
    @VoiceGenderParameter voiceGender: String,
    @VoiceNumberParameter voiceNumber: String,
    @ScoreParameter score: String,
    private val soundPlayer: SoundPlayer<PinYinSoundInfo>,
) : ViewModel() {
    val finalScore = score.toIntOrNull() ?: 0

    private val correctPinyinSoundInfo = PinYinSoundInfo(
        pinyin = Pinyin(initial, final),
        tone = Tone.valueOf(goodTone),
        voiceGender = runCatching { VoiceGender.valueOf(voiceGender) }.getOrNull()
            ?: VoiceGender.FEMININ,
        voiceNumber = runCatching { VoiceNumber.valueOf(voiceNumber) }.getOrNull()
            ?: VoiceNumber.ONE
    )
    private val badPinYinSoundInfo = correctPinyinSoundInfo.withNewTone(Tone.valueOf(selectedTone))
    private val _enableButtons: MutableLiveData<Boolean> = MutableLiveData(true)
    val enableButtons: LiveData<Boolean> get() = _enableButtons
    val goodTone = correctPinyinSoundInfo.tone
    val badTone = badPinYinSoundInfo.tone

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
        if (tone == correctPinyinSoundInfo.tone) {
            onGoodToneSelected()
        } else {
            onBadToneSelected()
        }
    }

    private fun onBadToneSelected() {
        soundPlayer.prepareSound(badPinYinSoundInfo)
    }

    private fun onGoodToneSelected() {
        soundPlayer.prepareSound(correctPinyinSoundInfo)
    }
}