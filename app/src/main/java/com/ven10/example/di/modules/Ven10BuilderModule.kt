package com.ven10.example.di.modules

import com.ven10.example.di.modules.ui.HomeModule
import com.ven10.example.di.scopes.PerActivity
import com.ven10.example.ui.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class Ven10BuilderModule {


    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun bindHomeActivity(): HomeActivity


}