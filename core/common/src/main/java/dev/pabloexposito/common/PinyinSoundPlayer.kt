package dev.pabloexposito.common

import android.content.Context
import android.content.res.Resources
import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.pabloexposito.model.data.PinYinSoundInfo
import javax.inject.Inject

class PinyinSoundPlayer @Inject constructor(
    @ApplicationContext private val context: Context,
    private val resourceResolver: ResourceResolver<PinYinSoundInfo>,
) : SoundPlayer<PinYinSoundInfo> {
    private val _playerState: MutableLiveData<PlayerState> = MutableLiveData()
    override val playerState: MutableLiveData<PlayerState> get() = _playerState

    private val mediaPlayer: MediaPlayer = MediaPlayer().apply {
        setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        )
        setOnPreparedListener { mP ->
            _playerState.postValue(PlayerState.ReadyToPlay {
                mP.start()
            })
        }
        setOnErrorListener { mediaPlayer, i, i2 ->
            _playerState.postValue(PlayerState.Error)
            true
        }
        setOnCompletionListener {
            _playerState.postValue(PlayerState.Completed)
        }
    }

    override fun prepareSound(soundInfo: PinYinSoundInfo) {
        resourceResolver(soundInfo)
            .onSuccess { rawRes ->
                with(mediaPlayer) {
                    if (isPlaying) stop()
                    reset()
                    kotlin.runCatching {
                        context.resources.openRawResourceFd(rawRes)?.let { fileDescriptor ->
                            setDataSource(
                                fileDescriptor.fileDescriptor,
                                fileDescriptor.startOffset,
                                fileDescriptor.length
                            )
                            prepareAsync()
                        }
                    }.onFailure(::handleSoundPreparationError)
                }
            }
            .onFailure { error ->
                _playerState.postValue(PlayerState.Error)
            }
    }

    override fun replaySound() {
        when (playerState.value) {
            PlayerState.Completed -> mediaPlayer.start()
            else -> {}
        }
    }

    private fun handleSoundPreparationError(throwable: Throwable) {
        //TODO Log Error with timber
        //TODO Define Errors
        when (throwable) {
            is Resources.NotFoundException -> {
                _playerState.postValue(PlayerState.Error)
            }

            is IllegalStateException -> _playerState.postValue(PlayerState.Error)
            else -> _playerState.postValue(PlayerState.Error)
        }
    }

    override fun onRelease() {
        mediaPlayer.reset()
        mediaPlayer.release()
    }

}