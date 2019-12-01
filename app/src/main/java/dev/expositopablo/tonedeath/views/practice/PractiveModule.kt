package dev.expositopablo.tonedeath.views.practice

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val practiceModule = module {
    viewModel(override = true) { PracticeViewModel(get()) }
}