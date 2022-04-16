package com.joncasagrande.bottlerocket.module

import com.joncasagrande.bottlerocket.viewModel.MainActivityViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { MainActivityViewModel() }

}