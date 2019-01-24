package com.ven10.example.di.modules

import android.content.Context

import com.ven10.example.App
import com.ven10.example.di.modules.local.Ven10DbModule
import com.ven10.example.di.modules.network.RetrofitModule
import com.ven10.example.di.scopes.Ven10AppScope
import dagger.Binds
import dagger.Module

@Module(includes = [RetrofitModule::class, Ven10DbModule::class])
abstract class Ven10AppModule {

    @Binds
    @Ven10AppScope
    abstract fun provideApplicationContext(application: App): Context
}