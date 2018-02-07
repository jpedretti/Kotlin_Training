package com.example.jpedretti.kotlintraining

import android.app.Application
import android.app.NotificationManager
import android.content.Context
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.startKoin
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(myModule))
    }
}

val myModule : Module = applicationContext {
    viewModel { DIViewModel(get(), get()) } // get() will resolve Repository instance
    //singleton by default
//    provide { androidApplication().getSystemService(Context.NOTIFICATION_SERVICE)
//            as NotificationManager  }
    //no singleton
    factory { androidApplication().getSystemService(Context.NOTIFICATION_SERVICE)
            as NotificationManager  }

    // Define bean with type MyServiceImpl and additional type MyService
    //provide { TestServiceImpl() } bind TestService::class
    // Define bean with type MyService
    provide { TestServiceImpl() as TestService }
}