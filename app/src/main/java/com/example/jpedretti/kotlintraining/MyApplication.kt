package com.example.jpedretti.kotlintraining

import android.app.Application
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.autoAndroidModule

class MyApplication : Application(), KodeinAware {
    override val kodein by Kodein.lazy {
        import(autoAndroidModule(this@MyApplication))
        bind<TestService>() with singleton { TestServiceImpl() }
        bind<DIViewModel>() with singleton { DIViewModel(instance(), this) }
    }
}