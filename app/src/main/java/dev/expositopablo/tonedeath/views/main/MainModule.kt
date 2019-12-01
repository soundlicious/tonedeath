package dev.expositopablo.tonedeath.views.main

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel(override = true) { MainViewModel(get()) }
}