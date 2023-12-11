package dev.pabloexposito.tonedeath.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.Provides
import dev.pabloexposito.data.repository.AppPinYinRepository
import dev.pabloexposito.data.repository.PinyinRepository

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @ActivityRetainedScoped
    @Provides
    fun providePinyinRepository(): PinyinRepository {
        return AppPinYinRepository()
    }
}