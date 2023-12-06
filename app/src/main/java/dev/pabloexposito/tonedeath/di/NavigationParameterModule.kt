package dev.pabloexposito.tonedeath.di

import androidx.lifecycle.SavedStateHandle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dev.pabloexposito.common.FINAL_PARAMETER
import dev.pabloexposito.common.FinalParameter
import dev.pabloexposito.common.GOOD_TONE
import dev.pabloexposito.common.GoodToneParameter
import dev.pabloexposito.common.INITIAL_PARAMETER
import dev.pabloexposito.common.InitialParameter
import dev.pabloexposito.common.SCORE
import dev.pabloexposito.common.SELECTED_TONE
import dev.pabloexposito.common.ScoreParameter
import dev.pabloexposito.common.SelectedToneParameter
import dev.pabloexposito.common.VOICE_GENDER
import dev.pabloexposito.common.VOICE_NUMBER
import dev.pabloexposito.common.VoiceGenderParameter
import dev.pabloexposito.common.VoiceNumberParameter

@Module
@InstallIn(ViewModelComponent::class)
class NavigationParameterModule {
    @Provides
    @InitialParameter
    @ViewModelScoped
    fun provideInitialParameter(savedStateHandle: SavedStateHandle): String =
        savedStateHandle.get<String>(INITIAL_PARAMETER)
            ?: throw IllegalArgumentException("Initial parameter couldn't be retrieved")

    @Provides
    @FinalParameter
    @ViewModelScoped
    fun provideFinalParameter(savedStateHandle: SavedStateHandle): String =
        savedStateHandle.get<String>(FINAL_PARAMETER)
            ?: throw IllegalArgumentException("Final parameter couldn't be retrieved")


    @Provides
    @SelectedToneParameter
    @ViewModelScoped
    fun provideSelectedToneParameter(savedStateHandle: SavedStateHandle): String =
        savedStateHandle.get<String>(SELECTED_TONE)
            ?: throw IllegalArgumentException("Pinyin parameter couldn't be retrieved")


    @Provides
    @GoodToneParameter
    @ViewModelScoped
    fun provideGoodToneParameter(savedStateHandle: SavedStateHandle): String =
        savedStateHandle.get<String>(GOOD_TONE)
            ?: throw IllegalArgumentException("Pinyin parameter couldn't be retrieved")


    @Provides
    @VoiceGenderParameter
    @ViewModelScoped
    fun provideVoiceGenderParameter(savedStateHandle: SavedStateHandle): String =
        savedStateHandle.get<String>(VOICE_GENDER)
            ?: throw IllegalArgumentException("Pinyin parameter couldn't be retrieved")

    @Provides
    @VoiceNumberParameter
    @ViewModelScoped
    fun provideVoiceNumberParameter(savedStateHandle: SavedStateHandle): String =
        savedStateHandle.get<String>(VOICE_NUMBER)
            ?: throw IllegalArgumentException("Pinyin parameter couldn't be retrieved")


    @Provides
    @ScoreParameter
    @ViewModelScoped
    fun provideScoreParameter(savedStateHandle: SavedStateHandle): String =
        savedStateHandle.get<String>(SCORE)
            ?: throw IllegalArgumentException("Score parameter couldn't be retrieved")

}