package com.example.jpedretti.kotlintraining

import android.app.Application
import android.app.NotificationManager
import android.content.Context
import com.example.jpedretti.kotlintraining.business.*
import com.example.jpedretti.kotlintraining.viewModel.DIViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.startKoin
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.Module
import android.net.ConnectivityManager
import com.example.jpedretti.kotlintraining.provider.*


class MyApplication : Application() {

    private val swapiHttpProviderName = "http"
    private val swapiOfflineProviderName = "offline"

    private val appModule: Module = org.koin.dsl.module.applicationContext {
        viewModel { DIViewModel(get(), get(), get(), get()) } // get() will resolve Repository instance

        // Define bean with type MyServiceImpl and additional type MyService
        //provide { TestBusinessImpl() } bind TestBusiness::class
        // Define bean with type MyService
        provide { TestBusinessImpl() as TestBusiness }
        provide {
            NotificationProviderImpl(get(), get(), getString(R.string.notification_channel_id))
                    as NotificationProvider
        }
        provide { ConnectivityProviderImpl(get()) as ConnectivityProvider }
        provide { SwapiBusinessImpl(get(swapiHttpProviderName), get(swapiOfflineProviderName), get())
                as SwapiBusiness
        }
        provide(swapiHttpProviderName) { SwapiPlanetHttpProviderImpl() as SwapiPlanetProvider }
        provide(swapiOfflineProviderName) { SwapiPlanetOfflineProviderImpl()
                as SwapiPlanetProvider }
    }

    private val systemServicesModule: Module = org.koin.dsl.module.applicationContext {
        //singleton by default
        //provide { androidApplication().getSystemService(Context.NOTIFICATION_SERVICE)
        //    as NotificationProvider  }
        //no singleton
        factory {
            androidApplication().getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager
        }
        factory {
            androidApplication().getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager
        }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, systemServicesModule))
    }
}