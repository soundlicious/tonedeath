package dev.expositopablo.tonedeath.di;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dev.expositopablo.tonedeath.ToneDeathApplication;
import dev.expositopablo.tonedeath.data.db.AppDataManager;
import dev.expositopablo.tonedeath.data.db.DataManager;
import dev.expositopablo.tonedeath.data.db.PinyinDatabase;
import dev.expositopablo.tonedeath.data.db.dao.PinyinDao;

@Module
public class RepositoryModule {


    private final SharedPreferences sharedPref;
    private PinyinDatabase pinyinDatabase;

    @Inject
    public RepositoryModule(ToneDeathApplication application) {
        pinyinDatabase= PinyinDatabase.getDatabase(application);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Singleton
    @Provides
    public PinyinDatabase providesPinyinDatabase() {
        return pinyinDatabase;
    }

    @Provides
    @Singleton
    public PinyinDao providePinyinDao(PinyinDatabase database){
        return database.pinyinDao();
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPrefs(){return sharedPref;}

    @Provides
    @Singleton
    public DataManager provideDataManager(){return new AppDataManager(pinyinDatabase, sharedPref);}
}
