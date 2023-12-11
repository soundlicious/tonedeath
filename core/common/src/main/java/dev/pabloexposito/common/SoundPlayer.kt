package dev.pabloexposito.common

import androidx.lifecycle.LiveData

interface SoundPlayer<T> {
    val playerState: LiveData<PlayerState>
    fun prepareSound(soundInfo: T)
    fun onRelease()
    fun replaySound()
}