package dev.expositopablo.tonedeath.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dev.expositopablo.tonedeath.learning.InitFinalDetailActivity;
import dev.expositopablo.tonedeath.learning.InitFinalListActivity;
import dev.expositopablo.tonedeath.learning.ListeningActivity;
import dev.expositopablo.tonedeath.main.MainActivity;
import dev.expositopablo.tonedeath.practice.PracticeActivity;

@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector
    public abstract MainActivity contributeMainActivity();
    @ContributesAndroidInjector
    public abstract InitFinalListActivity contributeInitFinalListActivity();
    @ContributesAndroidInjector
    public abstract InitFinalDetailActivity contributeInitFinalDetailActivity();
    @ContributesAndroidInjector
    public abstract PracticeActivity contributePracticeActivity();
    @ContributesAndroidInjector
    public abstract ListeningActivity contributeListeningActivity();
}
