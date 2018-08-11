package dev.expositopablo.tonedeath;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import com.amitshekhar.DebugDB;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dev.expositopablo.tonedeath.di.AppModule;
import dev.expositopablo.tonedeath.di.DaggerAppComponent;
import dev.expositopablo.tonedeath.di.RepositoryModule;

public class ToneDeathApplication extends Application implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .repositoryModule(new RepositoryModule(this))
                .build()
                .inject(this);

        if (BuildConfig.DEBUG){
            Log.i("TONEDEATHAPP", DebugDB.getAddressLog());
        }
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }
}
