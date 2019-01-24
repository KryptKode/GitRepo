package com.ven10.example.di.modules.network


import com.ven10.example.BuildConfig
import com.ven10.example.api.GithubService
import com.ven10.example.di.scopes.Ven10AppScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [OkhttpClientModule::class, NetworkFactoriesModule::class])
class RetrofitModule {

    @Provides
    @Ven10AppScope
    fun provideRetrofit(client: OkHttpClient, gsonConverterFactory: GsonConverterFactory, rxJavaCallAdapterFactory: RxJava2CallAdapterFactory): Retrofit{
        val builder = Retrofit.Builder()
        builder.client(client)
        builder.addCallAdapterFactory(rxJavaCallAdapterFactory)
        builder.addConverterFactory(gsonConverterFactory)
        builder.baseUrl(BuildConfig.BASE_URL)
        return builder.build()
    }



    @Ven10AppScope
    @Provides
    fun provideGitHubService(retrofit: Retrofit): GithubService {
        return retrofit.create(GithubService::class.java)
    }
}