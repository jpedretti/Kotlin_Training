package com.example.jpedretti.kotlintraining

import android.app.Application
import android.app.NotificationManager
import android.content.Context
import com.example.jpedretti.kotlintraining.services.*
import com.example.jpedretti.kotlintraining.viewModels.DIViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.startKoin
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.Module

class MyApplication : Application() {

    private val appModule: Module = org.koin.dsl.module.applicationContext {
        viewModel { DIViewModel(get(), get(), get()) } // get() will resolve Repository instance

        // Define bean with type MyServiceImpl and additional type MyService
        //provide { TestServiceImpl() } bind TestService::class
        // Define bean with type MyService
        provide { TestServiceImpl() as TestService }
        provide { NotificationServiceImpl(get(), get(), getString(R.string.notification_channel_id))
                as NotificationService }
        provide { ResourcesServiceImpl(get()) as ResourcesService }
    }

    private val systemServicesModule: Module = org.koin.dsl.module.applicationContext {
        //singleton by default
        //provide { androidApplication().getSystemService(Context.NOTIFICATION_SERVICE)
        //    as NotificationManager  }
        //no singleton
        factory { androidApplication().getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, systemServicesModule))
    }
}