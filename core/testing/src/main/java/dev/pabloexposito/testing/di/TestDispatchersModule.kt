package dev.pabloexposito.testing.di

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import dev.pabloexposito.common.Dispatcher
import dev.pabloexposito.common.DispatchersModule
import dev.pabloexposito.common.TDDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestDispatcher

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DispatchersModule::class],
)
object TestDispatchersModule {
    @Provides
    @Dispatcher(TDDispatchers.IO)
    fun providesIODispatcher(testDispatcher: TestDispatcher): CoroutineDispatcher = testDispatcher

    @Provides
    @Dispatcher(TDDispatchers.Default)
    fun providesDefaultDispatcher(
        testDispatcher: TestDispatcher,
    ): CoroutineDispatcher = testDispatcher
}