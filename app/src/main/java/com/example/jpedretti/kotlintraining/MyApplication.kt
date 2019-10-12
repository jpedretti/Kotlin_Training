package com.example.jpedretti.kotlintraining

import android.app.Application
import android.app.NotificationManager
import android.content.Context
import android.net.ConnectivityManager
import com.example.jpedretti.kotlintraining.business.SwapiBusiness
import com.example.jpedretti.kotlintraining.business.SwapiBusinessImpl
import com.example.jpedretti.kotlintraining.business.TestBusiness
import com.example.jpedretti.kotlintraining.business.TestBusinessImpl
import com.example.jpedretti.kotlintraining.provider.*
import com.example.jpedretti.kotlintraining.viewModel.DIViewModel
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module


class MyApplication : Application() {

    private val swapiHttpProviderName = "http"
    private val swapiOfflineProviderName = "offline"

    private val appModule = module {
        viewModel { DIViewModel(get(), get(), get(), get()) } // get() will resolve Repository instance

        // Define bean with type MyServiceImpl and additional type MyService
        //single { TestBusinessImpl() } bind TestBusiness::class
        // Define bean with type MyService
        single { TestBusinessImpl() as TestBusiness }
        single {
            NotificationProviderImpl(get(), get(), getString(R.string.notification_channel_id))
                    as NotificationProvider
        }
        single { ConnectivityProviderImpl(get()) as ConnectivityProvider }
        single { SwapiBusinessImpl(get(named(swapiHttpProviderName)), get(named(swapiOfflineProviderName)), get())
                as SwapiBusiness
        }
        single(named(swapiHttpProviderName)) { SwapiPlanetHttpProviderImpl() as SwapiPlanetProvider }
        single(named(swapiOfflineProviderName)) { SwapiPlanetOfflineProviderImpl()
                as SwapiPlanetProvider }
    }

    private val systemServicesModule = module {
        //singleton by default
        //single { androidApplication().getSystemService(Context.NOTIFICATION_SERVICE)
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
        startKoin {
            androidContext(this@MyApplication.applicationContext)
            modules(listOf(appModule, systemServicesModule))
        }
        getKoin().declare(this)
    }
}