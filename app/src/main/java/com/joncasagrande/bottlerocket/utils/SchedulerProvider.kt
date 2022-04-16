package com.joncasagrande.bottlerocket.utils

import io.reactivex.Scheduler

interface SchedulerProvider{
    fun io(): Scheduler
    fun main(): Scheduler
    fun newThread(): Scheduler
}