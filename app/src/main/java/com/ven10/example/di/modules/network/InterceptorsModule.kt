package com.ven10.example.di.modules.network


import com.ven10.example.di.scopes.Ven10AppScope
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

@Module
class InterceptorsModule{

    @Provides
    @Ven10AppScope
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            message -> Timber.e(message)
        }).setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}