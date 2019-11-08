package com.joncasagrande.bottlerocket.module

import com.joncasagrande.bottlerocket.utils.SchedulerProviderImpl
import org.koin.dsl.module

val schedulerModule = module {
    single { SchedulerProviderImpl() }

}