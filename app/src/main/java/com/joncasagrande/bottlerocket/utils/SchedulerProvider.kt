package com.joncasagrande.bottlerocket.utils

import io.reactivex.Scheduler

interface SchedulerProvider{
    fun io(): Scheduler
    fun computation(): Scheduler
    fun ui(): Scheduler
    fun newThread(): Scheduler
}