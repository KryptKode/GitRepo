package com.ven10.example.di.components


import com.ven10.example.App
import com.ven10.example.di.modules.Ven10AppModule
import com.ven10.example.di.modules.Ven10BuilderModule
import com.ven10.example.di.scopes.Ven10AppScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication

@Ven10AppScope
@Component(modules = [AndroidInjectionModule::class, AndroidSupportInjectionModule::class,
        Ven10BuilderModule::class, Ven10AppModule::class])
interface Ven10Component: AndroidInjector<DaggerApplication> {
    fun inject(application: App)

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun bindApplication(application: App): Builder

        fun build(): Ven10Component
    }
}