package dev.expositopablo.tonedeath.di;


import dagger.Subcomponent;
import dev.expositopablo.tonedeath.learning.InitFinalViewModel;
import dev.expositopablo.tonedeath.learning.ListeningViewModel;
import dev.expositopablo.tonedeath.practice.PracticeViewModel;

@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    InitFinalViewModel  initFinalViewModel();
    ListeningViewModel  listeningViewModel();
    PracticeViewModel   practiceViewModel();
}
