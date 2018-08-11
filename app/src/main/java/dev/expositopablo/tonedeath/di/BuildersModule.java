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
    abstract MainActivity contributeMainActivity();
    @ContributesAndroidInjector
    abstract InitFinalListActivity contributeInitFinalListActivity();
    @ContributesAndroidInjector
    abstract InitFinalDetailActivity contributeInitFinalDetailActivity();
    @ContributesAndroidInjector
    abstract PracticeActivity contributePracticeActivity();
    @ContributesAndroidInjector
    abstract ListeningActivity contributeListeningActivity();
}
