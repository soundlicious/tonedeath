package dev.expositopablo.tonedeath.di;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dev.expositopablo.tonedeath.ToneDeathApplication;

@Module(subcomponents = ViewModelSubComponent.class)
public class AppModule {
    private Application application;

    public AppModule(ToneDeathApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application providesApplication() {
        return application;
    }

    @Singleton
    @Provides
    public ViewModelProvider.Factory provideViewModelFactory(
            ViewModelSubComponent.Builder viewModelSubComponent) {

        return new ViewModelFactory(viewModelSubComponent.build());
    }

}
