package com.joncasagrande.bottlerocket.module

import com.joncasagrande.bottlerocket.ui.viewModel.MainActivityViewModel
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

val viewModelModule = module {
    viewModel { MainActivityViewModel(get(), get()) }
}