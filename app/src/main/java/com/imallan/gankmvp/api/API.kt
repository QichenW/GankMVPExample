package com.imallan.gankmvp.api

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class API {

    private object Holder {
        val INSTANCE: GankService = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpClient.Builder().addNetworkInterceptor(StethoInterceptor()).build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .let {
                    it.create(GankService::class.java)
                }
    }

    companion object {
        val instance: GankService by lazy { Holder.INSTANCE }
        const val BASE_URL = "http://gank.io/api/"
    }
}
