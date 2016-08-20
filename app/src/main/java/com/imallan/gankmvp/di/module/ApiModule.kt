package com.imallan.gankmvp.di.module

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.imallan.gankmvp.api.GankService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class ApiModule(val debuggable: Boolean) {

    companion object {
        const val BASE_URL = "http://gank.io/api/"
    }

    @Provides
    @Singleton
    fun providesGankService(retrofit: Retrofit): GankService {
        return retrofit.create(GankService::class.java)
    }

    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            if (debuggable) {
                addNetworkInterceptor(StethoInterceptor())
            }
        }.build()
    }

}
