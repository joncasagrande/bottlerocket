package com.joncasagrande.bottlerocket.utils

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerProviderImpl: SchedulerProvider {
    override fun main() = AndroidSchedulers.mainThread()
    override fun io() = Schedulers.io()
    override fun newThread() = Schedulers.newThread()
}