package com.ven10.example

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import com.ven10.example.di.components.DaggerVen10Component
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

class App: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerVen10Component.builder().bindApplication(this).build()
    }


    override fun onCreate() {
        super.onCreate()
        initLogger()
        initializeLeakCanary()
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())

        }


    }


    private fun initializeLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }

}