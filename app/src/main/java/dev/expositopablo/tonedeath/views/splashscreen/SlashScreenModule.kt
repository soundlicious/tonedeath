package dev.expositopablo.tonedeath.views.splashscreen

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashScreenModule = module {
    viewModel(override = true) { SplashScreenViewModel(get())}
}