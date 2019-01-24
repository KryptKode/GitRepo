package com.ven10.example.di.modules.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ven10.example.di.scopes.Ven10AppScope
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkFactoriesModule {

    @Provides
    @Ven10AppScope
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Ven10AppScope
    fun provideRxJavaAdapterFactory(): RxJava2CallAdapterFactory{
        return RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
    }

    @Provides
    @Ven10AppScope
    fun provideGson(): Gson{
        val builder = GsonBuilder()
        return builder.create()
    }
}