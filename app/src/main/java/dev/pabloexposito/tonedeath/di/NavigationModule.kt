package dev.pabloexposito.tonedeath.di


import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.pabloexposito.navigation.Navigator
import dev.pabloexposito.tonedeath.AppNavigator
import dev.pabloexposito.tonedeath.AppScreenTitleResolver
import dev.pabloexposito.tonedeath.ScreenTitleResolver
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Singleton
    @Provides
    fun provideScreenTitleResolver(@ApplicationContext context: Context): ScreenTitleResolver {
        return AppScreenTitleResolver(context)
    }

    @Singleton
    @Provides
    fun provideNavigator(screenTitleResolver: ScreenTitleResolver): Navigator {
        return AppNavigator(screenTitleResolver)
    }
}