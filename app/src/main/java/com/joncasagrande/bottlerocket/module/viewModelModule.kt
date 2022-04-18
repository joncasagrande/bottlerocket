package com.joncasagrande.bottlerocket.module

import com.joncasagrande.bottlerocket.ui.viewModel.ListStoreViewModel
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

val viewModelModule = module {
    viewModel { ListStoreViewModel(get()) }
}