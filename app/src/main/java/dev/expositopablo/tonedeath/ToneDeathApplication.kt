package dev.expositopablo.tonedeath

import android.app.Application
import dev.expositopablo.tonedeath.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ToneDeathApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ToneDeathApplication)
            modules(appModule)
        }
    }
}
