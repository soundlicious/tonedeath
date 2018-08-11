package dev.expositopablo.tonedeath.di;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dev.expositopablo.tonedeath.ToneDeathApplication;

@Module(subcomponents = ViewModelSubComponent.class)
public class AppModule {
    Application application;

    public AppModule(ToneDeathApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return application;
    }

    @Singleton
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(
            ViewModelSubComponent.Builder viewModelSubComponent) {

        return new ViewModelFactory(viewModelSubComponent.build());
    }

}
