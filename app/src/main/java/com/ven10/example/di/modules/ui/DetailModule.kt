package com.ven10.example.di.modules.ui

import com.ven10.example.di.scopes.PerActivity
import com.ven10.example.ui.detail.DetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DetailModule {

    @ContributesAndroidInjector
    @PerActivity
    abstract fun provideDetailFragment(): DetailFragment
}