package dev.expositopablo.tonedeath.di

import android.preference.PreferenceManager
import dev.expositopablo.tonedeath.androidutils.ext.AndroidSchedulerProvider
import dev.expositopablo.tonedeath.data.db.AppDataManager
import dev.expositopablo.tonedeath.data.db.DataManager
import dev.expositopablo.tonedeath.data.db.PinyinDatabase
import org.koin.dsl.module

val appModule = module {
    single { PreferenceManager.getDefaultSharedPreferences(get()); }
    single { PinyinDatabase.getDatabase(get()) }
    single { AppDataManager(get(), get(), AndroidSchedulerProvider()) as DataManager}
}