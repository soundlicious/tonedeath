package dev.pabloexposito.testing

import androidx.lifecycle.MutableLiveData
import dev.pabloexposito.common.PlayerState
import dev.pabloexposito.common.SoundPlayer
import dev.pabloexposito.model.data.PinYinSoundInfo

class FakeSoundPlayer : SoundPlayer<PinYinSoundInfo> {
    override val playerState: MutableLiveData<PlayerState> = MutableLiveData()
    var lastPinYinSoundInfo: PinYinSoundInfo? = null

    override fun prepareSound(soundInfo: PinYinSoundInfo) {
        lastPinYinSoundInfo = soundInfo
    }

    override fun onRelease() {

    }

    override fun replaySound() {
        emitPlayerState(PlayerState.Completed)
    }

    fun emitPlayerState(state: PlayerState) {
        playerState.postValue(state)
    }
}