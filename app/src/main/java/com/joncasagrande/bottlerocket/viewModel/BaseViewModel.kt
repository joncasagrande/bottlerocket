package com.joncasagrande.bottlerocket.viewModel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {
    val disposables = CompositeDisposable();

    @Override
    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}