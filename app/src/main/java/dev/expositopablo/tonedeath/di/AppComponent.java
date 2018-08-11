package dev.expositopablo.tonedeath.di;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dev.expositopablo.tonedeath.ToneDeathApplication;
import dev.expositopablo.tonedeath.data.db.DataManager;
import dev.expositopablo.tonedeath.learning.InitFinalViewModel;
import dev.expositopablo.tonedeath.learning.ListeningViewModel;
import dev.expositopablo.tonedeath.practice.PracticeViewModel;
import dev.expositopablo.tonedeath.uselesswidget.UselessWidget;

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, BuildersModule.class, RepositoryModule.class})
public interface AppComponent {
//    @Component.Builder
//    interface Builder {
//        @BindsInstance Builder application(Application application);
//        AppComponent build();
//    }
    void inject(ToneDeathApplication app);
    void inject(DataManager dataManager);
    void inject(InitFinalViewModel viewModel);
    void inject(PracticeViewModel viewModel);
    void inject(ListeningViewModel viewModel);
    void inject(UselessWidget uselessWidget);
}
