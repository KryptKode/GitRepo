package com.ven10.example.di.modules

import com.ven10.example.di.scopes.PerActivity
import com.ven10.example.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class Ven10BuilderModule {

    @PerActivity
    @ContributesAndroidInjector()
    abstract fun bindHomeActivity(): MainActivity

}