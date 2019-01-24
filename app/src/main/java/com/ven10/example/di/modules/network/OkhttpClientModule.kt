package com.ven10.example.di.modules.network

import android.content.Context
import com.ven10.example.di.scopes.Ven10AppScope
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import com.ven10.example.utils.Constants
import java.io.File
import java.util.concurrent.TimeUnit

@Module(includes = [InterceptorsModule::class])
class OkhttpClientModule {

    @Ven10AppScope
    @Provides
    fun provideFile(context: Context): File{
        return File(context.cacheDir, Constants.OK_HTTP_CACHE)
    }

    @Ven10AppScope
    @Provides
    fun provideCache(file: File): Cache?{
        return Cache(file, 10 * 1000 * 1000)
    }

    @Ven10AppScope
    @Provides
    fun provideOkhttpClient(cache: Cache?, httpInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(httpInterceptor)
        builder.cache(cache)
        builder.retryOnConnectionFailure(true)
        builder.writeTimeout(Constants.WRITE_TIME_OUT, TimeUnit.SECONDS)
        builder.connectTimeout(Constants.CONNECT_TIME_OUT, TimeUnit.SECONDS)

        return builder.build()
    }
}