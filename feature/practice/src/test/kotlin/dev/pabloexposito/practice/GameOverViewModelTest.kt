package dev.pabloexposito.practice

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.pabloexposito.core.data.VoiceGender
import dev.pabloexposito.model.data.Tone
import dev.pabloexposito.model.data.VoiceNumber
import dev.pabloexposito.testing.FakeSoundPlayer
import dev.pabloexposito.testing.MainDispatcherRule
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test

class GameOverViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainDispatcherRule()

    private lateinit var viewModel:GameOverViewModel
    private lateinit var fakeSoundPlayer: FakeSoundPlayer

    @Before
    fun setUp() {
        fakeSoundPlayer = FakeSoundPlayer()
        viewModel = GameOverViewModel(
            initial = "a",
            final = "b",
            selectedTone = Tone.TONE1.toString(),
            goodTone = Tone.TONE2.toString(),
            voiceGender = VoiceGender.FEMININ.toString(),
            voiceNumber = VoiceNumber.ONE.toString(),
            score = 0.toString(),
            soundPlayer = fakeSoundPlayer
        )
    }

    @Test
    fun `When playing correct tone, soundplayer use the good pinyinsoundinfo`() {
        viewModel.onToneSelected(Tone.TONE2)
        fakeSoundPlayer.lastPinYinSoundInfo?.let {
            assertEquals(it.pinyin.initial, "a")
            assertEquals(it.pinyin.final, "b")
            assertEquals(it.voiceNumber, VoiceNumber.ONE)
            assertEquals(it.voiceGender, VoiceGender.FEMININ)
            assertEquals(it.tone, Tone.TONE2)
        }
    }

    @Test
    fun `When playing selected tone, soundplayer use the good pinyinsoundinfo`() {
        viewModel.onToneSelected(Tone.TONE1)
        fakeSoundPlayer.lastPinYinSoundInfo?.let {
            assertEquals(it.pinyin.initial, "a")
            assertEquals(it.pinyin.final, "b")
            assertEquals(it.voiceNumber, VoiceNumber.ONE)
            assertEquals(it.voiceGender, VoiceGender.FEMININ)
            assertEquals(it.tone, Tone.TONE1)
        }
    }
}