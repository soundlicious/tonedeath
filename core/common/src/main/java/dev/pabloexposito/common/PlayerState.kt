package dev.pabloexposito.common

sealed class PlayerState {
    data class ReadyToPlay(val execute: () -> Unit) : PlayerState()
    data object Error : PlayerState()
    data object Completed : PlayerState()
}