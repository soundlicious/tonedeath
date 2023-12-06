package dev.pabloexposito.practice

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.pabloexposito.common.PlayerState
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import dev.pabloexposito.model.data.Tone
import dev.pabloexposito.testing.FakeNavigator
import dev.pabloexposito.testing.FakePinyinRepository
import dev.pabloexposito.testing.FakeSoundPlayer
import dev.pabloexposito.testing.MainDispatcherRule
import dev.pabloexposito.testing.getOrAwaitValue
import kotlinx.coroutines.runBlocking

class PracticeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainDispatcherRule()

    private lateinit var viewModel: PracticeViewModel
    private lateinit var fakeNavigator: FakeNavigator
    private lateinit var fakeSoundPlayer: FakeSoundPlayer
    private lateinit var fakePinyinRepository: FakePinyinRepository

    @Before
    fun setUp() {
        fakeNavigator = FakeNavigator()
        fakeSoundPlayer = FakeSoundPlayer()
        fakePinyinRepository = FakePinyinRepository()
        viewModel = PracticeViewModel(
            navigator = fakeNavigator,
            pinyinRepository = fakePinyinRepository,
            soundPlayer = fakeSoundPlayer,
        )
    }

    @Test
    fun `when PlayerState is Completed, displayButton is set to true`() {
        fakeSoundPlayer.emitPlayerState(PlayerState.Completed)
        assertEquals(true, viewModel.displayButton.getOrAwaitValue())
    }

    @Test
    fun `when PlayerState is Error, new pinyin is prepared`() {
        val initialPinyin = fakeSoundPlayer.lastPinYinSoundInfo
        fakeSoundPlayer.emitPlayerState(PlayerState.Error)
        val newPinyin = fakeSoundPlayer.lastPinYinSoundInfo
        assertNotEquals(initialPinyin, newPinyin)
    }

    @Test
    fun `when correct tone is selected, score increases`() {
        runBlocking {
            val correctTone = fakePinyinRepository.currentSoundInfo.tone
            assertEquals(0, viewModel.score.getOrAwaitValue())
            viewModel.onToneSelected(correctTone)
            fakeSoundPlayer.emitPlayerState(PlayerState.Completed)
            assertEquals(PlayerState.Completed, fakeSoundPlayer.playerState.getOrAwaitValue())
            assertEquals(1, viewModel.score.getOrAwaitValue())
        }

    }

    @Test
    fun `when wrong tone is selected, navigates to game over`() {
        val correctTone = fakePinyinRepository.currentSoundInfo.tone
        val wrongTone = Tone.entries.first { it != correctTone }
        viewModel.onToneSelected(wrongTone)
        assertNotNull(fakeNavigator.lastScreen)
        assertEquals(GameOverScreen, fakeNavigator.lastScreen?.first)
    }
}