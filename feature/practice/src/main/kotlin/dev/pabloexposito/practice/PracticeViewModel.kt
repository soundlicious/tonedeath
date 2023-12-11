package dev.pabloexposito.practice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.pabloexposito.model.data.Tone
import dev.pabloexposito.data.repository.PinyinRepository
import dev.pabloexposito.common.PlayerState
import dev.pabloexposito.common.SoundPlayer
import dev.pabloexposito.model.data.PinYinSoundInfo
import dev.pabloexposito.navigation.Navigator
import javax.inject.Inject

@HiltViewModel
internal class PracticeViewModel @Inject constructor(
    navigator: Navigator,
    private val pinyinRepository: PinyinRepository,
    private val soundPlayer: SoundPlayer<PinYinSoundInfo>,
) : Navigator by navigator, ViewModel() {

    private var currentPinYinSoundInfo: PinYinSoundInfo
    private var currentScore = 0
    private val _score: MutableLiveData<Int> = MutableLiveData(currentScore)
    val score: LiveData<Int> = _score
    private val _displayButton: MutableLiveData<Boolean> = MutableLiveData(false)
    val displayButton: LiveData<Boolean> get() = _displayButton
    private val playerStateObserver = Observer<PlayerState> { playerState ->
        when (playerState) {
            is PlayerState.Completed -> _displayButton.value = true
            is PlayerState.Error -> getRandomPinyin()
            is PlayerState.ReadyToPlay -> playerState.execute()
        }
    }

    init {
        soundPlayer.playerState.observeForever(playerStateObserver)
        currentPinYinSoundInfo = pinyinRepository.getRandomPinYinSoundInfo()
        getRandomPinyin()
    }

    private fun getRandomPinyin() {
        _displayButton.value = false
        currentPinYinSoundInfo = pinyinRepository.getRandomPinYinSoundInfo()
        soundPlayer.prepareSound(currentPinYinSoundInfo)
    }

    fun onToneSelected(tone: Tone) {
        if (tone == currentPinYinSoundInfo.tone) {
            currentScore = minOf(currentScore + 1, 100)
            _score.value = currentScore
            getRandomPinyin()
        } else {
            navigateToGameOver(tone, currentPinYinSoundInfo)
        }
    }

    fun onPlayEvent() {
        soundPlayer.replaySound()
    }

    private fun navigateToGameOver(selectedTone: Tone, currentSoundInfo: PinYinSoundInfo) {
        with(currentSoundInfo) {
            navigateTo(
                screen = GameOverScreen,
                params = GameOverScreenParameters(
                    init = pinyin.initial,
                    final = pinyin.final,
                    score = score.value ?: 0,
                    goodTone = tone,
                    badTone = selectedTone,
                    voiceGender = voiceGender,
                    voiceNumber = voiceNumber
                ),
                rules = null
            )
        }

    }

    override fun onCleared() {
        soundPlayer.playerState.removeObserver(playerStateObserver)
        super.onCleared()
    }
}