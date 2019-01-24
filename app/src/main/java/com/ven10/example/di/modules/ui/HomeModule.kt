package com.ven10.example.di.modules.ui

import com.ven10.example.di.scopes.PerActivity
import com.ven10.example.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module()
abstract class HomeModule {

    @ContributesAndroidInjector
    @PerActivity
    abstract fun provideHomeFragment(): HomeFragment




}