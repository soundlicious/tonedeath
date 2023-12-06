package dev.pabloexposito.tonedeath.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.pabloexposito.common.AppPinyinResourceResolver
import dev.pabloexposito.common.PinyinSoundPlayer
import dev.pabloexposito.common.SoundPlayer
import dev.pabloexposito.model.data.PinYinSoundInfo
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SoundModule {

    @Singleton
    @Provides
    fun provideSoundPlayer(
        @ApplicationContext context: Context,
    ): SoundPlayer<PinYinSoundInfo> {
        return PinyinSoundPlayer(
            context = context,
            resourceResolver = AppPinyinResourceResolver(context = context)
        )
    }
}